#!/bin/bash
mongo<<EOF
use all_stocks
db.createCollection("stock_price")
EOF

mongoimport --db all_stocks --collection stock_price --headerline --file $WORKSPACE/stocks.csv
