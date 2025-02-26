import 'package:helpp/cities/cities_char.dart';
import 'package:helpp/cities/cities_char.dart' as cities_char;
import 'package:flutter/material.dart';
import 'package:helpp/BottomBarPages/favouritesPage.dart';
import 'package:helpp/cityPages/CityDetailsPage.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatefulWidget {
  const MyApp({super.key});

  @override
  State<MyApp> createState() => _MyAppState();
}


class _MyAppState extends State<MyApp> {
  List<City> cityinfo = [];
  List<City> matchingcities = [];
  List<String> urls = [];

  void _getCities() {
    setState(() {
      cityinfo = cities_char.getCities();
      urls = cities_char.getUrls();
      matchingcities = List.from(cityinfo); // Initially, show all cities
    });
  }

  void _filteredCities(String query) {
    setState(() {
      if (query.isEmpty) {
        matchingcities =
            List.from(cityinfo);
      } else {
        matchingcities = cityinfo
            .where((city) =>
            city.citiName.toLowerCase().contains(query.toLowerCase()))
            .toList();
      }
    });
  }

  @override
  void initState() {
    super.initState();
    _getCities();
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: searchbar(),
        bottomNavigationBar: bottomBar(),
        body: ListView(
          children: [
            SizedBox(height: 30),
            search(),
            SizedBox(height: 30),
            charactesOfCity(),
          ],
        ),
      ),
    );
  }


  Builder bottomBar()
  {
    return  Builder(
      builder: (context) => NavigationBar(
        destinations: const [
          NavigationDestination(icon: Icon(Icons.home), label: "home"),
          NavigationDestination(icon: Icon(Icons.star), label: "favourite"),
          NavigationDestination(icon: Icon(Icons.account_box), label: "user",),
        ],
      onDestinationSelected: (index) { //διαλεγει σε ποια σελιδα θα παει με βαση το index
          if (index == 1) { // If "Favorites" is clicked
            List<City> favoriteCities = matchingcities.where((city) => city.isFavorite).toList();
            Navigator.push(
                context,  // Now using the correct context
                  MaterialPageRoute(
                    builder: (context) => FavoritesPage(favoriteCities: favoriteCities),
                  ),
            );
          }
      },
    ),
  );

}

  Column charactesOfCity() {
    return Column(
      children: [
        SizedBox(
          height: 600,
          child: ListView.separated(
            itemCount: matchingcities.length,
            // Display all or filtered cities
            scrollDirection: Axis.vertical,
            padding: const EdgeInsets.only(left: 10, right: 10),
            separatorBuilder: (context, index) => Divider(),
              itemBuilder: (context, index) {
                return GestureDetector(
                  onTap: () {
                    // Navigate to the details page when tapping the container
                    Navigator.push(
                      context,
                      MaterialPageRoute(
                        builder: (context) => CityDetailsPage(city: matchingcities[index],),
                      ),
                    );
                  },
                  child: Container(
                    width: 300,
                    height: 90,
                    decoration: BoxDecoration(
                      color: matchingcities[index].boxColor,
                      borderRadius: BorderRadius.circular(10),
                    ),
                    child: Stack(
                      children: [
                        ClipRRect(
                          borderRadius: BorderRadius.circular(10),
                          child: Image(
                            image: NetworkImage(urls[index]),
                            fit: BoxFit.cover,
                            width: double.infinity,
                            height: double.infinity,
                          ),
                        ),
                        Center(
                          child: Text(
                            matchingcities[index].citiName,
                            style: TextStyle(
                              color: Colors.white,
                              fontSize: 22,
                              fontWeight: FontWeight.bold,
                            ),
                          ),
                        ),
                        Positioned(
                          top: 8,
                          right: 8,
                          child: GestureDetector(
                            onTap: () {
                              setState(() {
                                matchingcities[index].isFavorite = !matchingcities[index].isFavorite;
                              });
                            },
                            child: Icon(
                              Icons.star,
                              color: matchingcities[index].isFavorite ? Colors.yellow : Colors.grey,
                              size: 30,
                            ),
                          ),
                        ),
                      ],
                    ),
                  ),
                );
              }

          ),
        ),
      ],
    );
  }

  Container search() {
    return Container(
      margin: const EdgeInsets.all(10),
      decoration: BoxDecoration(
        borderRadius: BorderRadius.circular(10),
        color: Colors.white10,
        boxShadow: [
          BoxShadow(
            color: Colors.grey,
            blurRadius: 10,
            spreadRadius: 1,
            offset: const Offset(0, 0),
          ),
        ],
      ),
      child: TextField(
        onChanged: _filteredCities, // Call filtering function on text input
        decoration: InputDecoration(
          filled: true,
          fillColor: Colors.white,
          hintText: "Search for your destination",
          prefixIcon: Icon(Icons.search, color: Colors.black, size: 30),
          border: OutlineInputBorder(
            borderRadius: BorderRadius.circular(20),
            borderSide: BorderSide.none,
          ),
        ),
      ),
    );
  }

  AppBar searchbar() {
    return AppBar(
      backgroundColor: Colors.purple,
      centerTitle: true,
      title: Text(
        "Find your trip destination",
        style: TextStyle(
          color: Colors.black,
          fontSize: 20,
          fontWeight: FontWeight.bold,
        ),
      ),
    );
  }
}