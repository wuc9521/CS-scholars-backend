import csv
import psycopg2
import dotenv
from tqdm import tqdm
import time

def connect_db():
    try:
        conn = psycopg2.connect(
            host="74.208.14.127",
            database="cs_scholars",
            user="cs_scholars_admin",
            password=dotenv.get_key(".env", "DB_PASSWORD"),
        )
        return conn
    except (Exception, psycopg2.DatabaseError) as error:
        print("Error connecting to the database:", error)
        return None

def insert_data(conn, filepath):
    cursor = conn.cursor()
    insert_stmt = """
    INSERT INTO \"publish\" (pubid, pid) VALUES (%s, %s)
    ON CONFLICT DO NOTHING;
    """

    batch_size = 10000  # Adjusted batch size to a manageable number
    data_batch = []
    total_rows_inserted = 0

    with open(filepath, newline="", encoding="utf-8") as csvfile:
        reader = csv.reader(csvfile)
        next(reader)  # Skip the header row
        
        # Prepare to show progress
        total_rows = sum(1 for row in csv.reader(open(filepath))) - 1
        csvfile.seek(0)
        next(reader)  # Reset reader after counting

        progress_bar = tqdm(total=total_rows, desc="Inserting rows")

        for row in reader:
            try:
                pubid = int(row[0])
                pid = float(row[1]) if row[1] else None
                if pid is not None:
                    pid = int(pid)
                    data_batch.append((pubid, pid))
            except ValueError as ve:
                print(f"Error processing row: {ve}")
                continue
            
            if len(data_batch) >= batch_size:
                try:
                    cursor.executemany(insert_stmt, data_batch)
                    conn.commit()
                    progress_bar.update(len(data_batch))
                    total_rows_inserted += len(data_batch)
                    data_batch = []
                    time.sleep(5)  # Pause for a few seconds
                except (Exception, psycopg2.DatabaseError) as error:
                    print(f"Error during insertion: {error}")
                    conn.rollback()

        if data_batch:
            try:
                cursor.executemany(insert_stmt, data_batch)
                conn.commit()
                progress_bar.update(len(data_batch))
                total_rows_inserted += len(data_batch)
            except (Exception, psycopg2.DatabaseError) as error:
                print(f"Final batch insertion error: {error}")
                conn.rollback()

        progress_bar.close()

    cursor.close()

def main():
    conn = connect_db()
    if conn is not None:
        insert_data(conn, "pubs.csv")  # Ensure the file path is correct
        conn.close()
        print("Data insertion completed successfully.")

if __name__ == "__main__":
    main()
