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
    INSERT INTO publication (pubid, pid, pmid, doi)
    SELECT %s, %s, %s, %s WHERE NOT EXISTS (
        SELECT 1 FROM publication WHERE pubid = %s
    )
    """
    with open(filepath, newline='') as csvfile:
        reader = csv.reader(csvfile)
        next(reader) 
        for row in reader:
            pubid = int(row[0])
            pid = int(float(row[1]))
            pmid = row[2]
            doi = row[3]
            cursor.execute(insert_stmt, (pubid, pid, pmid, doi, pubid))
    conn.commit()
    cursor.close()

def main():
    conn = connect_db()
    if conn is not None:
        insert_data(conn, 'pubs.csv')  
        conn.close()
        print("Data inserted successfully.")

if __name__ == "__main__":
    main()
