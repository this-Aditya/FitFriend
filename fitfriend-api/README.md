# FitFriend API Documentation

The FitFriend API provides access to yoga, pranayama, and meditation information.

# Yoga Endpoints

### Get All Asanas

Endpoint: `GET /yoga/asanas`

Returns a list of all available asanas.

### Get Asana by ID

Endpoint: `GET /yoga/asanas/id/{id}`

Returns the details of a specific asana identified by its ID.

### Get Asana by Name

Endpoint: `GET /yoga/asanas/name/{name}`

Returns the details of a specific asana identified by its name.

### Insert a New Asana

Endpoint: `POST /yoga/asanas`

Inserts a new asana into the database. Requires the asana details in the request body.

### Update an Asana

Endpoint: `PATCH /yoga/asanas`

Updates an existing asana in the database. Requires the updated asana details in the request body.

### Delete an Asana

Endpoint: `DELETE /yoga/asanas/{id}`

Deletes a specific asana identified by its ID.

# Pranayama Endpoints

### Get All Pranayams

Endpoint: `GET /yoga/pranayams`

Returns a list of all available pranayams.

### Get Pranayam by ID

Endpoint: `GET /yoga/pranayams/id/{id}`

Returns the details of a specific pranayam identified by its ID.

### Get Pranayam by Name

Endpoint: `GET /yoga/pranayams/name/{name}`

Returns the details of a specific pranayam identified by its name.

### Insert a New Pranayam

Endpoint: `POST /yoga/pranayams`

Inserts a new pranayam into the database. Requires the pranayam details in the request body.

### Update a Pranayam

Endpoint: `PATCH /yoga/pranayams`

Updates an existing pranayam in the database. Requires the updated pranayam details in the request body.

### Delete a Pranayam

Endpoint: `DELETE /yoga/pranayams/{id}`

Deletes a specific pranayam identified by its ID.

# Meditation Endpoints

### Get All Meditations

Endpoint: `GET /yoga/meditations`

Returns a list of all available meditations.

### Get Meditation by ID

Endpoint: `GET /yoga/meditations/id/{id}`

Returns the details of a specific meditation identified by its ID.

### Get Meditation by Name

Endpoint: `GET /yoga/meditations/name/{name}`

Returns the details of a specific meditation identified by its name.

### Insert a New Meditation

Endpoint: `POST /yoga/meditations`

Inserts a new meditation into the database. Requires the meditation details in the request body.

### Update a Meditation

Endpoint: `PATCH /yoga/meditations`

Updates an existing meditation in the database. Requires the updated meditation details in the request body.

### Delete a Meditation

Endpoint: `DELETE /yoga/meditations/{id}`

Deletes a specific meditation identified by its ID.

# Running the API Locally

If you want to run this API locally, you can use `localhost:8080` as the base URL and port.

