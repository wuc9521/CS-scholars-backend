import csv
import psycopg2
from datetime import datetime
import dotenv

def connect_db():
    try:
        conn = psycopg2.connect(
            host='74.208.14.127',
            database='cs_scholars',
            user='cs_scholars_admin',
            password=dotenv.get_key('.env', 'DB_PASSWORD'))
        return conn
    except (Exception, psycopg2.DatabaseError) as error:
        print(error)

def insert_data(conn, filepath):
    cursor = conn.cursor()
    insert_stmt = """
    INSERT INTO grants (grantid, budget_start)
    VALUES (%s, %s)
    ON CONFLICT (grantid) DO NOTHING;
    """
    batch_size = 100
    data_batch = []

    with open(filepath, newline='') as csvfile:
        reader = csv.reader(csvfile)
        next(reader)  # Skip the header if there is one
        for i, row in enumerate(reader):
            grant_id = int(row[0])
            budget_start = datetime.strptime(row[2], '%m/%d/%Y').date()
            data_batch.append((grant_id, budget_start))

            if (i + 1) % batch_size == 0:
                cursor.executemany(insert_stmt, data_batch)
                conn.commit()
                print(f'Inserted {i + 1} rows')
                data_batch = []

        if data_batch:
            cursor.executemany(insert_stmt, data_batch)
            conn.commit()
            print(f'Inserted remaining {len(data_batch)} rows')
    
    cursor.close()

def main():
    conn = connect_db()
    if conn is not None:
        insert_data(conn, 'grants.csv')
        conn.close()
        print("Data inserted successfully.")

if __name__ == "__main__":
    main()
