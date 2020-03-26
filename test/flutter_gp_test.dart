import 'package:flutter/services.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:flutter_gp/flutter_gp.dart';

void main() {
  const MethodChannel channel = MethodChannel('flutter_plugin_google');

  TestWidgetsFlutterBinding.ensureInitialized();

  setUp(() {
    channel.setMockMethodCallHandler((MethodCall methodCall) async {
      return '42';
    });
  });

  tearDown(() {
    channel.setMockMethodCallHandler(null);
  });

  test('flutter_plugin_google', () async {
    expect(await FlutterGp.getGuId, '42');
  });
}
