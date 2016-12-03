# Mock Checkout Service

Mock implementation of a checkout service returning stubbed data
 
## Usage and behaviour

The mock service is providing basic functionality indicative of the behaviour the actual implementation will demonstrate
and is used temorarily during development within the resource objects. The mock service is not the one handling routing
of requests and resource consumption but for the purpose of this document we will document the behaviour of the mock
service from the perspective of the client of the whole checkout service application, i.e. we will explain what the
request values and the expected responses of the API operations look like.

## Operations and expected behaviour

Available endpoints and the request/response data types can be found at the Swagger UI page for the service under
/swagger/ui

#### Get countries
---

* Valid Website Ids are - 1,2 and 3

Any integer value will return this JSON array:

```json
[
  {
    "code": "USA",
    "name": "United States",
    "regions": []
  },
  {
    "code": "GBR",
    "name": "United Kingdom",
    "regions": []
  },
  {
    "code": "CAN",
    "name": "Canada",
    "regions": [
      "ON",
      "QC",
      "NS",
      "NB",
      "MB",
      "BC",
      "PE",
      "SK",
      "AB",
      "NL"
    ]
  },
  {
    "code": "AUS",
    "name": "Australia",
    "regions": [
      "NSW",
      "QLD",
      "SA",
      "NT",
      "VIC",
      "WA"
    ]
  }
]
```

Any other input will result to a 404 unless an invalid Website Id is supplied which will return a 400 Bad Request.

#### Validate new user details
---

Emails must adhere to this case-insensitive regular expression to be considered valid:
`^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,6}$`. In that case the value of the `emailAddressValid` field in the response
object will be *true*.

Examples:

* stefanos@moo.com
* blah.Chatter@mouth.full.org 
* a@b.c

Moreover, if an email provided contains the sub-string *"exists"*, the value of `existingAccount` is also set to *true*.

Examples:

* god-exists@cloud.s
* hashtag@exists.trends

In any other case, these values are set to *false*


