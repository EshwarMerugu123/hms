import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:bootstrap_grid/bootstrap_grid.dart';
import 'package:hms/Floors/NumberOfPersons.dart';
import 'package:hms/Services/Service.dart';
import 'package:http/http.dart' as http;

class RoomsScreen extends StatefulWidget {
  final int floorId;

  RoomsScreen({
    Key? key,
    required this.floorId,
  }) : super(key: key);

  @override
  _RoomsScreenState createState() => _RoomsScreenState();
}

class _RoomsScreenState extends State<RoomsScreen> {
  List<Map<String, dynamic>> roomsData = []; // List to store room data

  @override
  void initState() {
    super.initState();
    fetchData(); // Fetch data when the widget initializes
  }

  void fetchData() async {
    try {
      // Make an API call to fetch rooms data for the given floorId
      final response = await http.get(
        Uri.parse(
            '${ApiServices.baseUrl}floorsService/getRoomsByFloorId?floorId=${widget.floorId}'),
      );

      if (response.statusCode == 200) {
        // If the API call is successful, parse the response and update the state
        final List<dynamic> jsonData = jsonDecode(response.body);
        setState(() {
          roomsData = jsonData.cast<Map<String, dynamic>>();
        });
      } else {
        print('Failed to fetch rooms data');
      }
    } catch (exception) {
      print('Exception: $exception');
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Rooms in Floor'),
      ),
      body: BootstrapRow(
        children: [
          for (int i = 0; i < roomsData.length; i++)
            BootstrapCol(
              lg: 6,
              xl: 6,
              md: 6,
              xs: 6,
              child: Card(
                child: ListTile(
                  title: Text('${roomsData[i]['roomName']}'),
                  subtitle: Text('Room ${i + 1}'),
                  onTap: () {
                    // Navigate to room details screen
                    Navigator.push(
                      context,
                      MaterialPageRoute(
                        builder: (context) =>
                            NumberOfPersons(roomName: roomsData[i]['roomId']),
                      ),
                    );
                  },
                ),
              ),
            ),
        ],
      ),
    );
  }
}
