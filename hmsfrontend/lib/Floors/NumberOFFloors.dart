import 'dart:convert';
import 'package:flutter/material.dart';
import 'package:shared_preferences/shared_preferences.dart';
import 'package:bootstrap_grid/bootstrap_grid.dart';
import 'package:hms/Floors/RoomsScreen.dart';
import 'package:http/http.dart' as http;
import '../Services/Service.dart';

class NumberOFFloors extends StatefulWidget {
  const NumberOFFloors({Key? key}) : super(key: key);

  @override
  State<NumberOFFloors> createState() => _NumberOFFloorsState();
}

class _NumberOFFloorsState extends State<NumberOFFloors> {
  int floorCount = 0;
  List<String> floorNames = [];
  List<int> floorIds = []; // Store floorIds
  int roomCount = 0;
  int roomName = 0;
  List<dynamic> firstFloorRooms = [];
  List<dynamic> secondFloorRooms = [];
  List<dynamic> numberOfEmployees = [];

  @override
  void initState() {
    super.initState();
    fetchData(); // Fetch data when the widget initializes
  }

  void fetchData() async {
    try {
      final response = await http.get(
        Uri.parse(ApiServices.baseUrl + ApiServices.get_All_Floors),
      );

      if (response.statusCode == 200) {
        final List<dynamic> jsonList = jsonDecode(response.body);
        for (Map<String, dynamic> building in jsonList) {
          if (building['buildingName'] == 'FinTech') {
            setState(() {
              floorCount = building['floorCount'] ?? 0;
              for (Map<String, dynamic> floor in building['floors']) {
                // Print floor names for debugging
                floorNames.add(floor['floorName'] ?? '');
                floorIds.add(floor['floorId'] ?? ''); // Add floorId
              }
            });
            break; // Exit loop after finding the desired building
          }
        }
        // Save employees outside the loop, after all data is collected
      } else {
        print('failed to load');
      }
    } catch (exception) {
      print('Exception: $exception');
    }
  }

  @override
  Widget build(BuildContext context) {
    if (floorCount == 0) {
      return Scaffold(body: Center(child: CircularProgressIndicator()));
    } else {
      return Scaffold(
        appBar: AppBar(
          title: Text('Number of Floors'),
        ),
        body: BootstrapRow(
          children: List.generate(
            floorCount,
            (index) => BootstrapCol(
              lg: 6,
              xl: 6,
              md: 6,
              xs: 6,
              child: GestureDetector(
                onTap: () {
                  Navigator.push(
                    context,
                    MaterialPageRoute(
                      builder: (context) => RoomsScreen(
                        floorId: floorIds[index], // Pass floorId to RoomsScreen
                      ),
                    ),
                  );
                },
                child: Card(
                  child: Column(
                    mainAxisSize: MainAxisSize.min,
                    children: [
                      ListTile(
                        leading: Icon(Icons.album),
                        title: Text(floorNames[index]),
                      ),
                      Row(
                        mainAxisAlignment: MainAxisAlignment.end,
                        children: <Widget>[
                          TextButton(
                            child: Text('BUY TICKETS'),
                            onPressed: () {},
                          ),
                          SizedBox(width: 8),
                          TextButton(
                            child: Text('LISTEN'),
                            onPressed: () {},
                          ),
                          SizedBox(width: 8),
                        ],
                      ),
                    ],
                  ),
                ),
              ),
            ),
          ),
        ),
      );
    }
  }
}
