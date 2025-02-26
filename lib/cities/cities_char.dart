import 'package:flutter/material.dart';

class City {
  String citiName;
  String country;
  Color boxColor;
  bool isFavorite;
  int photoIndex;

  City({required this.citiName, required this.country, required this.boxColor,required this.isFavorite,required this.photoIndex});
}

Map<String,List<String>> infos ={
  "Thessaloniki":["DESCRIPTION","https://upload.wikimedia.org/wikipedia/commons/b/ba/White_Tower_and_Beach_front.jpg","🍽 FOOD:","🏛️ MONUMENTS: ","🌇 ACTIVITIES: ","👮 SAFETY ","🚦 TRANSPORTATION: ", "💸 BUDGET: " ],
  "Athens": ["DESCRIPTION","https://hips.hearstapps.com/hmg-prod/images/gettyimages-502036215-1561036618.jpg","🍽 FOOD:","🏛️ MONUMENTS: ","🌇 ACTIVITIES: ","👮 SAFETY ","🚦 TRANSPORTATION: ", "💸 BUDGET: "],
  "Rome":["DESCRIPTION","https://www.planetware.com/photos-large/I/italy-rome-colosseum-and-arch-of-constantine.jpg","🍽 FOOD:","🏛️ MONUMENTS: ","🌇 ACTIVITIES: ","👮 SAFETY ","🚦 TRANSPORTATION: ", "💸 BUDGET: "],
  "Paris":["DESCRIPTION","https://meinereise.freiepresse.de/fileadmin/Media/Medien/16584-Titelbild_FR-PA01_Paris_Original_von_Grafik_bearbeitet__c__lapas77_-_Fotolia.tif.jpg","🍽 FOOD:","🏛️ MONUMENTS: ","🌇 ACTIVITIES: ","👮 SAFETY ","🚦 TRANSPORTATION: ", "💸 BUDGET: "],
  "Amsterdam":["DESCRIPTION","https://static01.nyt.com/images/2023/09/24/multimedia/24-36Hrs-Amsterdam-01-01-cwqt/24-36Hrs-Amsterdam-01-01-cwqt-videoSixteenByNineJumbo1600.jpg","🍽 FOOD:","🏛️ MONUMENTS: ","🌇 ACTIVITIES: ","👮 SAFETY ","🚦 TRANSPORTATION: ", "💸 BUDGET: "],
  "London":["DESCRIPTION","https://dynamic-media-cdn.tripadvisor.com/media/photo-o/15/33/f5/de/london.jpg?w=1400&h=1400&s=1","🍽 FOOD:","🏛️ MONUMENTS: ","🌇 ACTIVITIES: ","👮 SAFETY ","🚦 TRANSPORTATION: ", "💸 BUDGET: "],
  "Berlin":["DESCRIPTION","https://www.stenaline.co.uk/content/dam/stenaline/en/images/destinations/germany/20150820_berlin-brandenburg-gate.jpeg","🍽 FOOD:","🏛️ MONUMENTS: ","🌇 ACTIVITIES: ","👮 SAFETY ","🚦 TRANSPORTATION: ", "💸 BUDGET: "],
  "Barcelona":["DESCRIPTION","https://sitgesluxuryrentals.com/wp-content/uploads/2017/05/barcelona1.jpg","🍽 FOOD:","🏛️ MONUMENTS: ","🌇 ACTIVITIES: ","👮 SAFETY ","🚦 TRANSPORTATION: ", "💸 BUDGET: "]
};


List<String> getUrls(){
  return ['https://parallaximag.gr/wp-content/uploads/2022/11/thessaloniki-lefkos-pirghos.jpg','https://hhotels.gr/wp-content/uploads/2024/04/shutterstock_2392162239.jpg','https://miro.medium.com/v2/resize:fit:1400/1*gfzxEKbwzFDI8L-h2FQSrw.jpeg',
'https://mediaim.expedia.com/destination/9/e1d3f7300985d7c45299839747925884.jpg?impolicy=fcrop&w=450&h=280&q=medium', 'https://upload.wikimedia.org/wikipedia/commons/thumb/b/be/KeizersgrachtReguliersgrachtAmsterdam.jpg/800px-KeizersgrachtReguliersgrachtAmsterdam.jpg',
  'https://upload.wikimedia.org/wikipedia/commons/thumb/6/67/London_Skyline_%28125508655%29.jpeg/640px-London_Skyline_%28125508655%29.jpeg','https://cdn5.travelconline.com/images/fit-in/2000x0/filters:quality(75):strip_metadata():format(webp)/https%3A%2F%2Ftr2storage.blob.core.windows.net%2Fimagenes%2FNdNuuN490sV9Am3bRM-1Qidsfap5Cyc1uz.jpeg',
  'https://www.rantapallo.fi/wp-content/uploads/2015/08/espanja-barcelona-ss.jpg'];
}

List<City> getCities() {
  return [
    City(
      citiName: "Thessaloniki",
      country: "Greece",
      boxColor: Colors.blueAccent,
      isFavorite: false,
      photoIndex: 0
    ),
    City(citiName: "Athens", country: "Greece", boxColor: Colors.blueAccent,isFavorite: false,photoIndex: 1),
    City(citiName: "Rome", country: "Italy", boxColor: Colors.red,isFavorite: false,photoIndex: 2),

    City(citiName: "Paris", country: "France", boxColor: Colors.blue,isFavorite: false,photoIndex: 3),
    City(citiName: "Amsterdam", country: "Netherlands", boxColor: const Color.fromARGB(255, 13, 21, 84), isFavorite: false,photoIndex: 4),

    City(citiName: "London", country: "United Kingdom", boxColor: Colors.purple,isFavorite: false,photoIndex: 5),
    City(citiName: "Berlin", country: "Germany", boxColor: Colors.yellow,isFavorite: false,photoIndex: 6),
    City(citiName: "Barcelona", country: "Spain", boxColor: Colors.red, isFavorite: false,photoIndex: 7),
  ];
}

 bool favoriteCity(City city)
{
   return city.isFavorite;
}

List<String> getInfos(String cityName) {
  return infos[cityName]!;
}

