import 'package:flutter/material.dart';
import 'package:hms/Services/Service.dart';
import 'package:http/http.dart' as http;
import 'dart:convert';

class NumberOfPersons extends StatefulWidget {
  final int roomName;

  NumberOfPersons({Key? key, required this.roomName}) : super(key: key);

  @override
  State<NumberOfPersons> createState() => _NumberOfPersonsState();
}

class _NumberOfPersonsState extends State<NumberOfPersons> {
  List<dynamic> roomEmployees = []; // List to store fetched employee data

  void fetchData() async {
    try {
      // Make an API call to fetch rooms data for the given roomName
      final response = await http.get(
        // Uri.parse('YOUR_API_URL_HERE?roomId=${widget.roomName}'),
        Uri.parse(
            "${ApiServices.baseUrl}employee/getEmployeesByRoomId?roomId=${widget.roomName}"),
      );

      if (response.statusCode == 200) {
        // If the API call is successful, parse the response and update the state
        setState(() {
          roomEmployees = jsonDecode(response.body);
        });
      } else {
        print('Failed to fetch rooms data');
      }
    } catch (exception) {
      print('Exception: $exception');
    }
  }

  @override
  void initState() {
    super.initState();
    fetchData(); // Fetch data when the widget initializes
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Room Details'),
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            Text('Details of room ${widget.roomName}'),
            SizedBox(height: 20),
            Expanded(
              child: SingleChildScrollView(
                scrollDirection: Axis.horizontal,
                child: SingleChildScrollView(
                  child: DataTable(
                    columnSpacing: 16, // Add spacing between columns
                    columns: [
                      DataColumn(
                        label: Container(
                          child: Text(
                            'EmployeeName',
                            style: TextStyle(fontWeight: FontWeight.bold),
                          ),
                          decoration: BoxDecoration(),
                        ),
                      ),
                      DataColumn(
                        label: Container(
                          child: Text(
                            'Email',
                            style: TextStyle(fontWeight: FontWeight.bold),
                          ),
                          decoration: BoxDecoration(),
                        ),
                      ),
                      DataColumn(
                        label: Container(
                          child: Text(
                            'Age',
                            style: TextStyle(fontWeight: FontWeight.bold),
                          ),
                          decoration: BoxDecoration(),
                        ),
                      ),
                      DataColumn(
                        label: Container(
                          child: Text(
                            'Gender',
                            style: TextStyle(fontWeight: FontWeight.bold),
                          ),
                          decoration: BoxDecoration(),
                        ),
                      ),
                      DataColumn(
                        label: Container(
                          child: Text(
                            'Address',
                            style: TextStyle(fontWeight: FontWeight.bold),
                          ),
                          decoration: BoxDecoration(),
                        ),
                      ),
                    ],
                    rows: roomEmployees.map<DataRow>((employee) {
                      return DataRow(
                        cells: [
                          DataCell(
                            Container(
                              child: Text(employee['employeeName'] ?? ''),
                              padding: EdgeInsets.symmetric(vertical: 8),
                            ),
                          ),
                          DataCell(
                            Container(
                              child: Text(employee['email'] ?? ''),
                              padding: EdgeInsets.symmetric(vertical: 8),
                            ),
                          ),
                          DataCell(
                            Container(
                              child: Text(employee['age'].toString() ?? ''),
                              padding: EdgeInsets.symmetric(vertical: 8),
                            ),
                          ),
                          DataCell(
                            Container(
                              child: Text(employee['gender'] ?? ''),
                              padding: EdgeInsets.symmetric(vertical: 8),
                            ),
                          ),
                          DataCell(
                            Container(
                              child: Text(employee['employeeAddress'] ?? ''),
                              padding: EdgeInsets.symmetric(vertical: 8),
                            ),
                          ),
                        ],
                      );
                    }).toList(),
                  ),
                ),
              ),
            ),
          ],
        ),
      ),
    );
  }
}
