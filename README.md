# Android code test description


This app was created using native language in Android studio. It makes requests to the specified API to display a list of customer name's with their 'expectedTime', this is achieved by using the ```Customer``` object with the ```TimeUtils``` class. The list is then ordered by the expected time closest time first, this ordering is done in the ```CustomerListAdapter```.

The ```MainActivity``` uses the ```CustomerRequestClient``` class to send a get request to the API using an instance of  ```HttpURLConnection```.
The app authenticates the API using the ```basicAuth``` String as an Authorization request property on the HttpURLConnection object. The API returns a ```JSON``` object, which the list of customers is then parsed from.

Profile image's of customers are loaded from within the ```CustomerListAdapter```. Their email address is given to the Gravatar image request API which returns either the customers profile image or a default placeholder icon. These images are injected directly into an id specified in an XML layout by the ```ProfileImageRequestClient```  with a 3rd party library called Picasso.

The nature of the service that the app provides means that it needs to reload automatically every 30 seconds, This was achieved through the use of a ```Handler``` and its postDelayed method. Within this I call the ```setUpCustomerListAdapter()``` method, which executes the previously described API calls. It is also important to call ```removeCallbacksAndMessages``` in the apps activity ```onStop()``` to avoid any memory leaks.

The app makes sure to catch any errors that might occur on the UI and display a user friendly error message to the customer in the form of a toast.

It is important when creating apps whose logic includes API requests and other 'heavy' operations, like loading images, to not block the main UI thread. This can lead to screen hanging and a bad user experience, in order to make sure the app is fluid and responsive I provide concurrency through the use of threads and handlers. In addition to this a 'Please wait...' loading dialog is created and displayed to the user  by the ```ProgressDialogFactory``` class whilst work is being done.

Another vital part of the development life cycle is a healthy amount of unit testing. The app uses AndroidJunit4 to test utilities, classes, request clients etc. In developing this way, one is able to first plan their code and then develop against their test specification. Doing this means We can be confident in our business logic before going on to develop for the customer-facing user interface.
