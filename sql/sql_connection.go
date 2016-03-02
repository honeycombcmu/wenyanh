/*
http://www.01happy.com/golang-go-sql-drive-mysql-connection-pooling/
import (
    "database/sql"
    _ "github.com/go-sql-driver/mysql"
)

//con, err := sql.Open("mysql", store.user+":"+store.password+"@/"+store.database)
con, err := sql.Open("mysql", "root:123456@128.2.7.38:3306/honeycomb?charset=utf8"）
defer con.Close()
*/

import (
    "database/sql"
    "fmt"
    _ "github.com/go-sql-driver/mysql"
    "log"
    "net/http"
)
 
var db *sql.DB
 
func init() {
    db, _ = sql.Open("mysql", "root:123456@128.2.7.38:3306/honeycomb?charset=utf8")
    db.SetMaxOpenConns(2000)
    db.SetMaxIdleConns(1000)
    db.Ping()
}
 
func main() {
    startHttpServer()
}
 
func startHttpServer() {
    http.HandleFunc("/pool", pool)
    err := http.ListenAndServe(":9090", nil)
    if err != nil {
        log.Fatal("ListenAndServe: ", err)
    }
}
 
func pool(w http.ResponseWriter, r *http.Request) {
    rows, err := db.Query("SELECT * FROM user limit 1")
    defer rows.Close()
    checkErr(err)
 
    columns, _ := rows.Columns()
    scanArgs := make([]interface{}, len(columns))
    values := make([]interface{}, len(columns))
    for j := range values {
        scanArgs[j] = &values[j]
    }
 
    record := make(map[string]string)
    for rows.Next() {
        //Put the row data into the dict of the record
        err = rows.Scan(scanArgs...)
        for i, col := range values {
            if col != nil {
                record[columns[i]] = string(col.([]byte))
            }
        }
    }
 
    fmt.Println(record)
    fmt.Fprintln(w, "finish")
}
 
func checkErr(err error) {
    if err != nil {
        fmt.Println(err)
        panic(err)
    }
}