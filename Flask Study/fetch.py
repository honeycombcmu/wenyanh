import pycassa
from pycassa.pool import ConnectionPool
from pycassa.columnfamily import ColumnFamily

# connecting to Cassandra
pool = ConnectionPool('Keyspace1')

# getting a ColumnFamily
col_fam = ColumnFamily(pool, 'ColumnFamily1')

# inserting Data
col_fam.insert('row_key', {'col_name':'col_val', 'col_name2':'col_val2'})

# getting Data
col_fam.get('row_key')
# {'col_name': 'col_val', 'col_name2': 'col_val2'}

# counting
col_fam.get_count('row_key')
