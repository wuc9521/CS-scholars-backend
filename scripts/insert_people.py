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
    INSERT INTO people (pid, locid, majorarea, hindex, name)
    VALUES (%s, %s, %s, %s, %s)
    ON CONFLICT (pid) DO NOTHING;  
    """ # Assuming 'pid' is the primary key
    with open(filepath, newline='', encoding='utf-8') as csvfile:
        reader = csv.reader(csvfile)
        next(reader)  # Skip the header row
        for row in reader:
            pid = int(float(row[0]))
            locid = int(float(row[1]))  # Convert to integer if locid is represented as a float
            majorarea = row[2]
            hindex = int(float(row[3])) if row[3] else None  # Convert to integer, handle empty values
            name = row[4]
            cursor.execute(insert_stmt, (pid, locid, majorarea, hindex, name))
    conn.commit()
    cursor.close()

def main():
    conn = connect_db()
    if conn is not None:
        insert_data(conn, 'people.csv')  # Make sure the file name is correct
        conn.close()
        print("Data inserted successfully.")

if __name__ == "__main__":
    main()
