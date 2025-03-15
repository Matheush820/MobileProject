import React, { useEffect } from 'react';
import { SafeAreaView, StatusBar, Text, View, Image } from 'react-native';
import { LinearGradient } from 'expo-linear-gradient'; 
import { NavigationContainer } from '@react-navigation/native';
import { createStackNavigator } from '@react-navigation/stack';
import styles from './styles/globalStyle';  
import logo from './assets/logo.png';
import LoginScreen from './screens/LoginScreen';
import CreateScreen from './screens/CreateScreen';

// Tela de SplashScreen
const SplashScreen = ({ navigation }) => {
  useEffect(() => {
    const timer = setTimeout(() => {
      navigation.replace('Login'); 
    }, 3000);

    return () => clearTimeout(timer);
  }, [navigation]);

  return (
    <SafeAreaView style={{ flex: 1 }}>
      <StatusBar barStyle="dark-content" backgroundColor="#058EB8" />
      <LinearGradient
        colors={['#058EB8', '#006381']} 
        style={styles.container}
      >
        <View style={styles.topWave} />
        <View style={styles.content}>
          <Image source={logo} style={styles.logo} />
          <Text style={styles.title}>SalaFacil</Text>
          <Text style={[styles.subtitle, { fontFamily: 'serif' }]}>Space</Text>
        </View>
        <View style={styles.bottomWave} />
      </LinearGradient>
    </SafeAreaView>
  );
};

const Stack = createStackNavigator();

const App = () => {
  return (
    <NavigationContainer>
      <Stack.Navigator initialRouteName="Splash">
        <Stack.Screen 
          name="Splash" 
          component={SplashScreen} 
          options={{ headerShown: false }}  
        />
        <Stack.Screen 
          name="Login" 
          component={LoginScreen} 
          options={{ headerShown: false }}  
        />
        <Stack.Screen 
          name="Create" 
          component={CreateScreen} 
          options={{ headerShown: false }}  
        />
      </Stack.Navigator>
    </NavigationContainer>
  );
};


export default App;
