import React from 'react';
import { View, Text, TouchableOpacity } from 'react-native';
import { useNavigation } from '@react-navigation/native';
import { Ionicons } from '@expo/vector-icons'; // Ícones modernos
import styles from '../styles/ProfileScreenStyle';

const ProfileScreen = () => {
  const navigation = useNavigation();

  const handleResetPassword = () => {
    console.log('Redefinir senha acionado');
  };

  const handleLogout = () => {
    navigation.navigate('Login');
  };

  return (
    <View style={styles.container}>
      {/* Botão de Voltar */}
      <TouchableOpacity
        style={styles.backButton}
        onPress={() => navigation.goBack()} // Navega para a tela anterior
      >
        <Ionicons name="arrow-back" size={30} color="#1794B9" />
      </TouchableOpacity>

      {/* Informações do Usuário */}
      <View style={styles.profileContainer}>
        <Text style={styles.name}>Nome do Usuário</Text>
        <Text style={styles.email}>email@example.com</Text>
      </View>

      {/* Botões de Ação */}
      <TouchableOpacity style={styles.button} onPress={() => navigation.navigate('Appointments')}>
        <Ionicons name="calendar-outline" size={20} color="white" />
        <Text style={styles.buttonText}>Minhas Reservas</Text>
      </TouchableOpacity>

      <TouchableOpacity style={styles.button} onPress={handleResetPassword}>
        <Ionicons name="key-outline" size={20} color="white" />
        <Text style={styles.buttonText}>Redefinir Senha</Text>
      </TouchableOpacity>

      {/* Sobre o Aplicativo */}
      <TouchableOpacity style={styles.button} onPress={() => navigation.navigate('AboutApp')}>
        <Ionicons name="information-circle-outline" size={20} color="white" />
        <Text style={styles.buttonText}>Sobre o Aplicativo</Text>
      </TouchableOpacity>

      {/* Botão de Logout mais abaixo */}
      <View style={styles.logoutContainer}>
        <TouchableOpacity style={styles.logoutButton} onPress={handleLogout}>
          <Ionicons name="log-out-outline" size={20} color="white" />
          <Text style={styles.logoutText}>Sair</Text>
        </TouchableOpacity>
      </View>
    </View>
  );
};

export default ProfileScreen;
