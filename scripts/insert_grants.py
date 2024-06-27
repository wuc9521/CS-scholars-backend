import csv
import psycopg2
from datetime import datetime

def connect_db():
    try:
        conn = psycopg2.connect(
            host='localhost',
            database='csscholars',
            user='postgres',
            password='password')
        return conn
    except (Exception, psycopg2.DatabaseError) as error:
        print(error)

def insert_data(conn, filepath):
    cursor = conn.cursor()
    insert_stmt = """
    INSERT INTO grants (grantid, budget_start)
    SELECT %s, %s WHERE NOT EXISTS (
        SELECT 1 FROM grants WHERE grantid = %s
    )
    """
    with open(filepath, newline='') as csvfile:
        reader = csv.reader(csvfile)
        next(reader)  # Skip the header if there is one
        for row in reader:
            grant_id = int(row[0])
            budget_start = datetime.strptime(row[2], '%m/%d/%Y').date()
            cursor.execute(insert_stmt, (grant_id, budget_start, grant_id))
    conn.commit()
    cursor.close()

def main():
    conn = connect_db()
    if conn is not None:
        insert_data(conn, 'grants.csv')
        conn.close()
        print("Data inserted successfully.")

if __name__ == "__main__":
    main()
