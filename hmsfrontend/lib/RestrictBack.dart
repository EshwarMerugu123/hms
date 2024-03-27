import 'package:flutter/material.dart';


class RestrictBack extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: MyHomePage(),
    );
  }
}

class MyHomePage extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Dashboard'),
      ),
      body: Center(
        child: ElevatedButton(
          onPressed: () {
            // Navigate to the Google page
            // Replace this with your navigation logic
            Navigator.push(
              context,
              MaterialPageRoute(builder: (context) => GooglePage()),
            );
          },
          child: Text('Go to Google Page'),
        ),
      ),
    );
  }
}

class GooglePage extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return WillPopScope(
      onWillPop: () async {
        // Show an alert dialog to confirm navigation back
        bool confirmExit = await showDialog(
          context: context,
          builder: (context) => AlertDialog(
            title: Text('Confirm'),
            content: Text('Are you sure you want to go back?'),
            actions: <Widget>[
              TextButton(
                onPressed: () {
                  Navigator.of(context).pop(false); // Stay on the current page
                },
                child: Text('No'),
              ),
              TextButton(
                onPressed: () {
                  Navigator.of(context).pop(true); // Navigate back
                },
                child: Text('Yes'),
              ),
            ],
          ),
        );
        return confirmExit ?? false; // Prevent default back button behavior if null
      },
      child: Scaffold(
        appBar: AppBar(
          title: Text('Google Page'),
        ),
        body: Center(
          child: Text('This is the Google page'),
        ),
      ),
    );
  }
}
