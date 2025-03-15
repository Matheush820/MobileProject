import React, { useState } from 'react';
import { SafeAreaView, StatusBar, Text, TextInput, TouchableOpacity, View, Image, Animated, ActivityIndicator } from 'react-native';
import { LinearGradient } from 'expo-linear-gradient';
import { Ionicons } from '@expo/vector-icons';
import styles from '../styles/LoginScreenStyle.js';

const LoginScreen = ({ navigation }) => { 
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [showPassword, setShowPassword] = useState(false);
  const [buttonAnimation, setButtonAnimation] = useState(new Animated.Value(0)); 
  const [loading, setLoading] = useState(false); 
  const [error, setError] = useState('');

  const handleLogin = async () => {
    if (!validateEmail(email)) {
      setError('Por favor, insira um email válido.');
      return;
    }

    setLoading(true);
    setError('');

    try {
      const response = await fetch('https://sua-api-backend.com/login', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({
          email: email,
          password: password,
        }),
      });

      const data = await response.json();

      if (response.ok) {
        console.log('Login bem-sucedido', data);
      
      } else {
        setError(data.message || 'Erro ao fazer login');
      }
    } catch (err) {
      console.error('Erro na API:', err);
      setError('Erro ao se conectar com o servidor');
    } finally {
      setLoading(false);
    }
  };

  const handleSignUp = () => {
    navigation.navigate('Create');  // Navegação para a tela de criação de conta
  };

  const validateEmail = (email) => {
    const re = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return re.test(String(email).toLowerCase());
  };

  const animateButton = () => {
    Animated.spring(buttonAnimation, {
      toValue: 1,
      friction: 5,
      tension: 100,
      useNativeDriver: true,
    }).start();
  };

  return (
    <SafeAreaView style={{ flex: 1 }}>
      <StatusBar barStyle="dark-content" />
      <LinearGradient colors={['#058EB8', '#006381']} style={styles.container}>
        <View style={styles.topSection}>
          <View style={styles.logoContainer}>
            <Image source={require('../assets/logo.png')} style={styles.logoImage} />
            <Text style={styles.logoText}>SalaFacil</Text>
            <Text style={styles.logoSubText}>Space</Text>
          </View>
        </View>

        <View style={styles.inputContainer}>
          <TextInput
            style={styles.input}
            placeholder="Email"
            placeholderTextColor="#aaa"
            keyboardType="email-address"
            value={email}
            onChangeText={setEmail}
          />
          <View style={styles.passwordContainer}>
            <TextInput
              style={styles.inputPassword}
              placeholder="Senha"
              placeholderTextColor="#aaa"
              secureTextEntry={!showPassword}
              value={password}
              onChangeText={setPassword}
            />
            <TouchableOpacity onPress={() => setShowPassword(!showPassword)}>
              <Ionicons
                name={showPassword ? 'eye-off' : 'eye'}
                size={24}
                color="#aaa"
              />
            </TouchableOpacity>
          </View>
        </View>

        {error ? <Text style={styles.errorText}>{error}</Text> : null}
        {loading && <ActivityIndicator size="large" color="#00C896" />}

        <View style={styles.buttonContainer}>
          <TouchableOpacity
            style={[styles.loginButton, { transform: [{ translateX: buttonAnimation.interpolate({ inputRange: [0, 1], outputRange: [0, 50] }) }] }]}
            onPress={() => { handleLogin(); animateButton(); }}
            disabled={loading} 
          >
            <Text style={styles.loginButtonText}>Entrar</Text>
          </TouchableOpacity>
        </View>

        <TouchableOpacity onPress={handleSignUp}>
          <Text style={styles.signUpLink}>Não tem conta? Clique aqui para se cadastrar.</Text>
        </TouchableOpacity>
        <Text style={styles.forgotPassword}>Esqueceu a senha?</Text>
        <Text style={styles.footerText}>Todos os direitos reservados - Equipe SalaFacil</Text>
      </LinearGradient>
    </SafeAreaView>
  );
};

export default LoginScreen;
