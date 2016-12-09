# Android code test description


This project was created in Android studio.

The app uses the specified API to display a list of customers with their 'expectedTime'. It does this by sending a get request to the API using a ```HttpURLConnection``` object. 
The app authenticates the API using the ```basicAuth``` String as an Authorization request property on the HttpURLConnection object. The API returns a ```JSON``` object, which a list of customers is then parsed from.
This list of customers is sorted according to the expectedTime is ascending order, this is done in the ```CustomerListAdapter```.

I go on to load the profile image of customers using their email address with the Gravatar image request API. To accomplish this I used a 3rd party library called Picasso which offers a simple way to load images directly into an id specified in an XML layout.

The nature of the service that the app provides means that it needs to reload automatically every 30 seconds, This was achieved through the use of a ```Handler``` and its postDelayed method. Within this I call the ```setUpCustomerListAdapter()``` method, which executes the previously described API calls. It is also important to call ```removeCallbacksAndMessages``` in the apps activity ```onStop()``` to avoid any memory leaks.

The app makes sure to catch any errors that might occur on the UI and display a user friendly error message to the customer in the form of a toast.

It is important when creating apps whose logic includes API requests and other 'heavy' operations, like loading images, to not block the main UI thread. This can lead to screen hanging and a bad user experience, in order to make sure the app is fluid and responsive I provide concurrency through the use of threads and handlers.

Another vital part of the development life cycle is a healthy amount of unit testing. The app uses AndroidJunit4 to test utilities, classes, correctly populating a list of customers etc.

In developing this way, one is able to first plan their code and then develop against their test specification. Doing this means We can be confident in our business logic before going on to develop  for the customer-facing user interface.

