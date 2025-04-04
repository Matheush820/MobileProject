import React, { useEffect } from 'react'; 
import { SafeAreaView, StatusBar, Text, View, Image } from 'react-native';
import { LinearGradient } from 'expo-linear-gradient'; 
import { NavigationContainer } from '@react-navigation/native';
import { createStackNavigator } from '@react-navigation/stack';
import styles from './styles/globalStyle';  
import logo from './assets/logo.png';
import LoginScreen from './screens/LoginScreen';
import CreateScreen from './screens/CreateScreen';
import HomeScreen from './screens/HomeScreen';
import RoomsScreen from './screens/RoomsScreen';
import LabDetailsScreen from './screens/LabDetailsScreen ';
import AgendamentoScreen from './screens/AgendamentoScreen';
import ResetPasswordScreen from './screens/ResetPasswordScreen.js';
import AppointmentsScreen from './screens/AppointmentsScreen.js';
import ProfileScreen from './screens/ProfileScreen';
import EditAppointmentScreen from '../ProjetoMobile/screens/EditAppointMentsScreen.js';
import AboutAppScreen from '../ProjetoMobile/screens/AboutAppScreen.js';
import CategoryDetails from '../ProjetoMobile/screens/CategoryDetailsScreen.js';
import LabsOverviewScreen from '../ProjetoMobile/screens/LabsOverviewScreen.js';

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
        <Stack.Screen
          name="Home"
          component={HomeScreen}
          options={{ headerShown: false }}
        />
        <Stack.Screen
          name="Rooms"
          component={RoomsScreen}
          options={{ headerShown: false }}
        />
        <Stack.Screen
          name="LabDetails"
          component={LabDetailsScreen}
          options={{ headerShown: false }}
        />
        <Stack.Screen
          name="Agendamento"
          component={AgendamentoScreen}
          options={{ headerShown: false }}
        />
        <Stack.Screen
          name="Password"
          component={ResetPasswordScreen}
          options={{ headerShown: false }}
        />

        <Stack.Screen
        name="Appointments"
        component={AppointmentsScreen}
        options={{ headerShown: false}}
        />
      <Stack.Screen
      name='Profile'
      component={ProfileScreen}
      options={{ headerShown: false}}
      />
      <Stack.Screen
      name='EditAppointMent'
      component={EditAppointmentScreen}
      options={{ headerShown: false}}
      />
      <Stack.Screen
      name='AboutApp'
      component={AboutAppScreen}
      options={{ headerShown: false}}
      />
      <Stack.Screen
      name='CategoryDetails'
      component={CategoryDetails}
      options={{ headerShown: false}}
      />
      <Stack.Screen
      name='LabsOverview'
      component={LabsOverviewScreen}
      options={{ headerShown: false}}
      />
      </Stack.Navigator>

    </NavigationContainer>
  );
};

export default App;
