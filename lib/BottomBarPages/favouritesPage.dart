import 'package:flutter/material.dart';
import 'package:helpp/cities/cities_char.dart';
import 'package:helpp/cityPages/CityDetailsPage.dart';
import 'package:helpp/cities/cities_char.dart' as cities_char;

class FavoritesPage extends StatefulWidget {
  final List<City> favoriteCities;

  FavoritesPage({required this.favoriteCities});

  @override
  _FavoritesPageState createState() => _FavoritesPageState();
}

class _FavoritesPageState extends State<FavoritesPage> {
  List<String> urls = [];

  @override
  void initState() {
    super.initState();
    _getUrls();
  }

  void _getUrls() {
    final fetchedUrls = cities_char.getUrls(); // Fetch URLs
    if (mounted) {
      setState(() {
        urls = fetchedUrls;
      });
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: welcoming(),
      body: mainBody(), // Call the method correctly
    );
  }

  AppBar welcoming() {
    return AppBar(
      title: Text(
        "Your Favorite Destinations",
        style: TextStyle(fontSize: 20, fontWeight: FontWeight.bold),
      ),
      backgroundColor: Colors.purple,
    );
  }

  Widget mainBody() {
    if (widget.favoriteCities.isEmpty) {
      return Center(
        child: Text(
          "No favorites yet :(",
          style: TextStyle(fontSize: 20, fontWeight: FontWeight.bold, color: Colors.black),
        ),
      );
    } else {
      SizedBox(width: 20,);
      return ListView.separated(
        itemCount: widget.favoriteCities.length,
        padding: const EdgeInsets.symmetric(horizontal: 10),
        separatorBuilder: (context, index) => Divider(),
        itemBuilder: (context, index) {
          return GestureDetector(
            onTap: () {
              Navigator.push(
                context,
                MaterialPageRoute(
                  builder: (context) => CityDetailsPage(city: widget.favoriteCities[index]),
                ),
              );
            },
            child: Container(
              width: 300,
              height: 90,
              decoration: BoxDecoration(
                color: widget.favoriteCities[index].boxColor,
                borderRadius: BorderRadius.circular(10),
              ),
              child: Stack(
                children: [
                  ClipRRect(
                    borderRadius: BorderRadius.circular(10),
                    child: urls.isNotEmpty && index < urls.length
                        ? Image.network(
                      urls[widget.favoriteCities[index].photoIndex],
                      fit: BoxFit.cover,
                      width: double.infinity,
                      height: double.infinity,
                      loadingBuilder: (context, child, loadingProgress) {
                        if (loadingProgress == null) return child;
                        return Center(child: CircularProgressIndicator());
                      },
                      errorBuilder: (context, error, stackTrace) {
                        return Container(
                          color: Colors.grey,
                          width: double.infinity,
                          height: double.infinity,
                          child: Center(child: Icon(Icons.error, color: Colors.red)),
                        );
                      },
                    )
                        : Container(
                      color: Colors.grey,
                      width: double.infinity,
                      height: double.infinity,
                      child: Center(child: CircularProgressIndicator()),
                    ),
                  ),
                  Center(
                    child: Text(
                      widget.favoriteCities[index].citiName,
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
                          widget.favoriteCities[index].isFavorite =
                          !widget.favoriteCities[index].isFavorite;
                        });
                      },
                      child: Icon(
                        Icons.star,
                        color: widget.favoriteCities[index].isFavorite ? Colors.yellow : Colors.grey,
                        size: 30,
                      ),
                    ),
                  ),
                ],
              ),
            ),
          );
        },
      );
    }
  }
}
