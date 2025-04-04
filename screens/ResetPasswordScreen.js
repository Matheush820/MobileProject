import React, { useState } from 'react';
import { View, Text, TextInput, TouchableOpacity, Alert, Image, StatusBar } from 'react-native';
import { Ionicons } from '@expo/vector-icons';
import styles from '../styles/ResetPasswordScreenStyle';

const ResetPasswordScreen = ({ navigation }) => {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [confirmPassword, setConfirmPassword] = useState('');

  const handleResetPassword = async () => {
    if (password !== confirmPassword) {
      Alert.alert('Erro', 'As senhas não coincidem.');
      return;
    }

    try {
      const response = await fetch('https://seu-backend.com/api/reset-password', {
        method: 'PUT',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({ email, password }),
      });

      if (response.ok) {
        Alert.alert('Sucesso', 'Senha redefinida com sucesso!');
        navigation.navigate('Login');
      } else {
        Alert.alert('Erro', 'Não foi possível redefinir a senha.');
      }
    } catch (error) {
      Alert.alert('Erro', 'Ocorreu um erro. Tente novamente.');
    }
  };

  return (
    <View style={styles.container}>
      <StatusBar barStyle="dark-content" />

      {/* Botão de Voltar no topo esquerdo */}
      <TouchableOpacity style={styles.backButton} onPress={() => navigation.goBack()}>
        <Ionicons name="arrow-back" size={30} color="#1C4C77" />
      </TouchableOpacity>

      {/* Seção do Topo */}
      <View style={styles.topSection}>
        <Image source={require('../assets/logo.png')} style={styles.logoImage} />
        <Text style={styles.logoText}>SalaFacil</Text>
        <Text style={styles.logoSubText}>Space</Text>
      </View>

      <View style={styles.inputContainer}>
  <TextInput
    style={styles.input}
    placeholder="Email"
    value={email}
    onChangeText={setEmail}
    keyboardType="email-address"
  />
  <TextInput
    style={styles.input}
    placeholder="Nova Senha"
    secureTextEntry
    value={password}
    onChangeText={setPassword}
  />
  <TextInput
    style={styles.input}
    placeholder="Confirme a Nova Senha"
    secureTextEntry
    value={confirmPassword}
    onChangeText={setConfirmPassword}
  />
</View>


      {/* Botão de Resetar Senha */}
      <TouchableOpacity style={styles.loginButton} onPress={handleResetPassword}>
        <Text style={styles.loginButtonText}>Redefinir Senha</Text>
      </TouchableOpacity>
    </View>
  );
};

export default ResetPasswordScreen;
