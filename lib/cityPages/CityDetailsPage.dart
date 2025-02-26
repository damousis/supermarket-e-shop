import 'package:flutter/material.dart';
import 'package:helpp/cities/cities_char.dart';

class CityDetailsPage extends StatelessWidget {
  final City city;
  List<String> cityInformation=[];

  CityDetailsPage({required this.city});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: Color.fromARGB(255, 228, 237, 255),
      appBar: appBar(city),
      body: cityInfo(city),
    bottomNavigationBar: BottomAppBar(
    child: Row(
      mainAxisAlignment: MainAxisAlignment.spaceEvenly,
      children: [
        Expanded(
          child: TextButton.icon(
          onPressed: () {
            Navigator.push(
              context,
              MaterialPageRoute(builder: (context) => RestaurantsPage(city: city)),
            );
          },
          icon: Icon(Icons.fastfood, color: Colors.purple),
          label: Text(
              "Restaurants",
              style: TextStyle(
                color: Colors.black,
                fontSize: 17,
              )
          ),
        ),
      ),
      Container(height: 40, width: 1, color: Colors.grey), // Divider Line
      Expanded(
        child: TextButton.icon(
          onPressed: () {
            Navigator.push(
              context,
              MaterialPageRoute(builder: (context) => MonumentsPage(city: city)),
            );
          },
          icon: Icon(Icons.museum, color: Colors.purple),
          label: Text(
              "Monuments",
              style: TextStyle(
                  color: Colors.black,
                fontSize: 17,
              )
          ),
         ),
        ),
       ],
      ),
     ),
    );

  }

  AppBar appBar(City city) {
    return AppBar(
      backgroundColor: city.boxColor,
      title: Text(
        "Welcome to: ${city.citiName}",
        style: TextStyle(
          fontSize: 23,
          fontWeight: FontWeight.bold,
          fontStyle: FontStyle.italic,
        ),
      ),
    );
  }

  Widget cityInfo(City city) {
    cityInformation = getInfos(city.citiName);
    return SingleChildScrollView(
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: [
          Padding(
            padding: const EdgeInsets.all(12.0),
            child: Text(
              cityInformation[0],
              textAlign: TextAlign.justify,
              style: TextStyle(
                fontSize: 16,
                height: 1.5,
                color: Colors.black87,
              ),
            ),
          ),
          SizedBox(height: 10),
          ClipRRect(
            borderRadius: BorderRadius.circular(12),
            child: Image.network(
              cityInformation[1],
              fit: BoxFit.cover,
              height: 200,
              width: double.infinity,
            ),
          ),
          SizedBox(height: 20),
          Padding(
            padding: const EdgeInsets.symmetric(horizontal: 12),
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                Text(cityInformation[2], style: infoTextStyle(Colors.green)),
                Text(cityInformation[3], style: infoTextStyle(Colors.green)),
                Text(cityInformation[4], style: infoTextStyle(Colors.green)),
                Text(cityInformation[5], style: infoTextStyle(Colors.orange)),
                Text(cityInformation[6], style: infoTextStyle(Colors.green)),
                Text(cityInformation[7], style: infoTextStyle(Colors.orange)),
              ],
            ),
          ),
        ],
      ),
    );
  }

  TextStyle infoTextStyle(Color color) {
    return TextStyle(
      fontSize: 18,
      fontWeight: FontWeight.bold,
      color: color,
    );
  }
}

// ----------------- New Pages -----------------

class RestaurantsPage extends StatelessWidget {
  final City city;

  RestaurantsPage({required this.city});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text("Restaurants in ${city.citiName}")),
      body: ListView(
        padding: EdgeInsets.all(12),
        children: [
          restaurantCard("Taverna Agora", "Traditional Greek food", "https://example.com/image1.jpg"),
          restaurantCard("Fast & Yummy", "Quick fast food options", "https://example.com/image2.jpg"),
          restaurantCard("Seafood Delight", "Fresh seafood specialties", "https://example.com/image3.jpg"),
        ],
      ),
    );
  }

  Widget restaurantCard(String name, String desc, String imageUrl) {
    return Card(
      elevation: 5,
      shape: RoundedRectangleBorder(borderRadius: BorderRadius.circular(12)),
      child: ListTile(
        leading: Image.network(imageUrl, width: 60, height: 60, fit: BoxFit.cover),
        title: Text(name, style: TextStyle(fontWeight: FontWeight.bold)),
        subtitle: Text(desc),
      ),
    );
  }
}

class MonumentsPage extends StatelessWidget {
  final City city;

  MonumentsPage({required this.city});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text("Monuments in ${city.citiName}")),
      body: ListView(
        padding: EdgeInsets.all(12),
        children: [
          monumentCard("White Tower", "Iconic historical landmark", "https://example.com/image4.jpg"),
          monumentCard("Archaeological Museum", "Ancient Greek artifacts", "https://example.com/image5.jpg"),
          monumentCard("Rotunda", "Historic Roman structure", "https://example.com/image6.jpg"),
        ],
      ),
    );
  }

  Widget monumentCard(String name, String desc, String imageUrl) {
    return Card(
      elevation: 5,
      shape: RoundedRectangleBorder(borderRadius: BorderRadius.circular(12)),
      child: ListTile(
        leading: Image.network(imageUrl, width: 60, height: 60, fit: BoxFit.cover),
        title: Text(name, style: TextStyle(fontWeight: FontWeight.bold)),
        subtitle: Text(desc),
      ),
    );
  }
}
