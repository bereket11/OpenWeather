# Weather_demo

The application presents the weather with the use of a RecycleView, each item of the RecycleView present the weather condition for every three hours and it contains an image, the date and time, and the weather condition.

The application is making use of the following technologies: Retrofit 2, RxJava, ButterKnife and espresso. Furthermore, is build based on the MVP architectural pattern. 

To use the application you need to installed it into your phone through Android Studio or with .apk format. Moreover, you need to have the wifi enable and give internet permission if it is asked. 

The application consists from on Activity class, the MainActivity that calls the fragment: Weather_view when is launch. This fragment is used as the view part of the application thus is implementing the IContract_Weather.IView_DataWeather interface and overrides the necessary methods that were predefined, to connect to the presenter and show the data on the screen. The presenter of the application is the class Weather_Presenter that is implementing the IContract_Weather.IPresenter_DataWeather to connect with the view and to add the logic of the application. 

The Weather_view is calling the presenter from the onCreateView(), the presenter is making use of the Rxjava and retrofit 2, to connect to the OpenWeatherMap API. The data are sent back from the API as JSON format. For the deserialization of them, the application is using GSON and the necessary POJO classes that have been added inside the project.The presenter after it receives the data back from the API is calling the view and sent the data, the view is displaying the data with a RecycleView. 

The RecycleView is making use of an adaptor(WeatherAdaptor) and the ViewHolder pattern. The adaptor receives the data, the row that it will use to display each item and the context of the class. Finally, the adaptor adds the data to the XML elements of the row. The Picasso library is also used to add the images from the given URL


Future Improvement
--------------------------------------------
If I had more time I was going to add location finding system to display the location of each user and not just London. Also, i was going to implement five separate recycled views, each of them was going to represent a different day was going to be viewed in horizontal mode. Furthermore, I could improve my MVP architecture by adding an Interactor to allow the presenter to communicate with the retrofit. Lastly, i was going to make the UI of the application more elegant and add some other functionality as well, like search.

You can run the demo in two ways:
Install the app by building the apk or clone the project to your local file and run it from Android Studio

Here is the link for Git - https://github.com/bereket11/OpenWeather.git


