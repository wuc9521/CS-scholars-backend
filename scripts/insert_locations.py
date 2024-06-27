import csv
import psycopg2

def connect_db():
    try:
        conn = psycopg2.connect(
            host='localhost',
            database='csscholars',
            user='postgres',
            password='password')  # Replace with your actual password
        return conn
    except (Exception, psycopg2.DatabaseError) as error:
        print("Error while connecting to the database:", error)

def insert_data(conn, filepath):
    cursor = conn.cursor()
    insert_stmt = """
    INSERT INTO locations (locid, location, city, state, country)
    VALUES (%s, %s, %s, %s, %s)
    ON CONFLICT (locid) DO NOTHING;
    """   # Assuming 'locid' is the primary key
    with open(filepath, newline='', encoding='utf-8') as csvfile:
        reader = csv.reader(csvfile)
        next(reader)  # Skip the header row
        for row in reader:
            locid = int(row[0])
            location = row[1]
            city = row[2]
            state = row[3]
            country = row[4]
            cursor.execute(insert_stmt, (locid, location, city, state, country))
    conn.commit()
    cursor.close()

def main():
    conn = connect_db()
    if conn is not None:
        insert_data(conn, 'locations.csv')  # Ensure the file name is correct
        conn.close()
        print("Data inserted successfully.")

if __name__ == "__main__":
    main()
