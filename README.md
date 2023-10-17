# Coffee Shop

This project demonstrates the implementation of two Java microservices for a fictive coffee shop, Orders Service and Barista Service. 

### Usage

You can interact with the Orders Service by making HTTP requests to its REST endpoints.<br>
For example:

- Adding an order:

```http
POST /orders

{
  "coffeeType": "espresso",
  "cupSize": "small",
  "milkType": "cow milk",
  "onSite": true,
  "price": 3.99
}
```

- List of all orders in progress:

```http
GET /orders
```


- Deleting/Cancelling an order:

```http
DELETE /orders/{orderId}
```

