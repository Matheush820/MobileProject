import React, { useState } from 'react';
import { SafeAreaView, StatusBar, Text, TextInput, TouchableOpacity, View, Image, ActivityIndicator } from 'react-native';
import { LinearGradient } from 'expo-linear-gradient';
import styles from '../styles/LoginScreenStyle.js';

const CreateScreen = ({ navigation }) => {
  const [name, setName] = useState('');
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [confirmPassword, setConfirmPassword] = useState('');
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState('');

  const handleSignUp = async () => {
    if (!name || !email || !password || !confirmPassword) {
      setError('Todos os campos são obrigatórios.');
      return;
    }

    if (password !== confirmPassword) {
      setError('As senhas não coincidem.');
      return;
    }

    setLoading(true);
    setError('');

    try {
      const response = await fetch('https://sua-api-backend.com/create', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({ name, email, password }),
      });

      const data = await response.json();

      if (response.ok) {
        console.log('Conta criada com sucesso', data);
        navigation.navigate('Login');
      } else {
        setError(data.message || 'Erro ao criar conta');
      }
    } catch (err) {
      console.error('Erro na API:', err);
      setError('Erro ao se conectar com o servidor');
    } finally {
      setLoading(false);
    }
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
          {error ? <Text style={styles.errorText}>{error}</Text> : null}

          <TextInput
            style={styles.input}
            placeholder="Nome"
            placeholderTextColor="#aaa"
            value={name}
            onChangeText={setName}
          />
          <TextInput
            style={styles.input}
            placeholder="Email"
            placeholderTextColor="#aaa"
            keyboardType="email-address"
            value={email}
            onChangeText={setEmail}
          />
          <TextInput
            style={styles.input}
            placeholder="Senha"
            placeholderTextColor="#aaa"
            secureTextEntry
            value={password}
            onChangeText={setPassword}
          />
          <TextInput
            style={styles.input}
            placeholder="Confirmar Senha"
            placeholderTextColor="#aaa"
            secureTextEntry
            value={confirmPassword}
            onChangeText={setConfirmPassword}
          />
        </View>

        {loading && <ActivityIndicator size="large" color="#00C896" />}

        <View style={styles.buttonContainer}>
          <TouchableOpacity style={styles.loginButton} onPress={handleSignUp} disabled={loading}>
            <Text style={styles.loginButtonText}>Cadastrar</Text>
          </TouchableOpacity>
        </View>

        <TouchableOpacity onPress={() => navigation.navigate('Login')}>
          <Text style={styles.signUpLink}>Já tem uma conta? Faça login.</Text>
        </TouchableOpacity>

        <Text style={styles.footerText}>Todos os direitos reservados - Equipe SalaFacil</Text>
      </LinearGradient>
    </SafeAreaView>
  );
};

export default CreateScreen;
