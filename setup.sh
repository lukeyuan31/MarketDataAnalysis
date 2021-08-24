#!/bin/bash
mongo<<EOF
use all_stock
db.createCollection("stock_price")
EOF

mongoimport --db all_stock --collection stock_price --type csv --headerline --file $WORKSPACE/stocks.csv
