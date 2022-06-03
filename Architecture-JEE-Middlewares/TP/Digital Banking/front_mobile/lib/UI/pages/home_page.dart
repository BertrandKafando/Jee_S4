import 'package:flutter/material.dart';

class Home extends StatelessWidget{
  @override
  Widget build(BuildContext context) {
    // TODO: implement build
   return Scaffold(
     appBar: AppBar(
       centerTitle: true,
       title: Text("Bank"),
     ),
     body: Center(
       child: Text("DIGITAL BANK"),
     ),
   );
  }

}