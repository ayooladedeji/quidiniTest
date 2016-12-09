# Android code test description


This project was created in Android studio.

The app uses the specified API to display a list of customers with their 'expectedTime'. It does this by sending a get request to the API using a ```HttpURLConnection``` object. 
The app authenticates the API using the ```basicAuth``` String as an Authorization request property on the HttpURLConnection object. the API returns a ```JSON``` object which a list of customers is then parsed from.
This list of customers is sorted according to the expectedTime is ascending order, this is done in the ```CustomerListAdapter```.

I go on to load the profile image of customers using the customers email address with the Gravatar image request API. To accomplish this I used a 3rd party library called Picasso which offers an easy and minimal time consuming way to load images directly into an id spefied in an XML layout.

The nature of the service that the app provides means that it needs to reload automatically every 30 seconds, This was acheived through the use of a ```Handler``` and its postDelayed method. Within this I call the ```setUpCustomerListAdapter()``` method, which executes the previously described API calls.

The app makes sure to catch any errors that might occur on the UI and display an error message to the customer in the form of a toast.

It is important when creating apps whose logic includes API requests and other 'heavy' operations like loading images to not block the main UI thread, this leads to screen hanging and a bad user experience. In order to make sure the app is fluid and responsive I provide concurrency throguh the use of threads and handlers.

Another vital part of the development life cycle is a healthy amount of unit testing, the app uses AndroidJunit4 to test utilities classes, correctly populating a list of customers ect.
in developing this way one is able to first plan their code and then develop against their test specification. Doing this means We can be confident in our business logic before going on to develop  for the customer facing user interface.

