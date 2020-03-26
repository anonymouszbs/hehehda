import 'dart:async';

import 'package:flutter/services.dart';

class FlutterGp {
  static const MethodChannel _channel =
      const MethodChannel('flutter_plugin_google');

  static Future<String> get getGuId async {
    final String version = await _channel.invokeMethod('getGuId');
    return version;
  }
}
