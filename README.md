# Buildout-01

## Usage

Please make sure to have a collection named ```buildout_01``` in mongodb

Run the jar file attached using below command inside the cloned folder
```
java -jar Buildout-01.jar
```

Access the APIs using ```http://localhost:8080/swagger-ui/index.html```

## API References
### GET /user
Request URL - http://localhost:8080/user

Response - 
```
[
  {
    "id": "66051740e8c4af2d916c186a",
    "username": "Sandeep Roy",
    "score": 10,
    "badgeList": [
      "Code Ninja"
    ]
  },
  {
    "id": "660518bb52cdd407a2ffc8f6",
    "username": "Laxmikant Nath",
    "score": 90,
    "badgeList": [
      "Code Master"
    ]
  }
]
```
### POST /user
Request URL - http://localhost:8080/user
Request Body -
```
{
  "username": "Sandeep Roy",
  "score": 10
}
```
Respone -
```
{
  "id": "66051740e8c4af2d916c186a",
  "username": "Sandeep Roy",
  "score": 10,
  "badgeList": [
    "Code Ninja"
  ]
}
```

### PUT /user/{user_id}
Allowed upto 3 Scores, Badges for each valid scores are shown as List

Request URL - http://localhost:8080/user/66051740e8c4af2d916c186a

Request Body - 
```
{
  "score": 58
}
```
Response -
```
{
  "id": "66051740e8c4af2d916c186a",
  "username": "Sandeep Roy",
  "score": 58,
  "badgeList": [
    "Code Ninja",
    "Code Champ"
  ]
}
```

### DELETE /user/{user_id}
Request URL - http://localhost:8080/user/66051740e8c4af2d916c186a

Response -

```
Deleted
```
