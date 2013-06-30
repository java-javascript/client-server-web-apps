#!/bin/bash

# Create an individual author
curl -i -X POST -H "Content-Type: application/json" \
-H "Accept: application/json" \
-d '{name: "Simon St. Laurent"}' \
http://localhost:8080/bookshop/authors

# Create an collection of authors
curl -i -X POST -H "Content-Type: application/json" \
-H "Accept: application/json" \
-d '[ {name: "Douglas Crockford"}, {name: "Michael Fogus"} ]' \
http://localhost:8080/bookshop/authors/jsonArray

# Delete the authors previously added
curl -i -X DELETE -H "Accept: application/json" http://localhost:8080/bookshop/authors/1
curl -i -X DELETE -H "Accept: application/json" http://localhost:8080/bookshop/authors/2
curl -i -X DELETE -H "Accept: application/json" http://localhost:8080/bookshop/authors/3

# Add books with associated authors
curl -i -X POST -H "Content-Type: application/json" \
-H "Accept: application/json" \
-d '{name:"JavaScript: The Good Parts",price:29.99,authors:[{name: "Douglas Crockford"}]}' \
http://localhost:8080/bookshop/books

curl -i -X POST -H "Content-Type: application/json" \
-H "Accept: application/json" \
-d '{name:"Functional JavaScript",price:29.99,authors:[{name: "Michael Fogus"}]}' \
http://localhost:8080/bookshop/books

curl -i -X POST -H "Content-Type: application/json" \
-H "Accept: application/json" \
-d '{name:"Introducing Elixir",price:19.99,authors:[{name: "Simon St. Laurent"}]}' \
http://localhost:8080/bookshop/books
