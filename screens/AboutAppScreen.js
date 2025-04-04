import React from 'react';
import { View, Text, StyleSheet, Image, TouchableOpacity } from 'react-native';
import { Ionicons } from '@expo/vector-icons'; // Para o ícone de seta
import { useNavigation } from '@react-navigation/native'; // Para navegação

const AboutAppScreen = () => {
  const navigation = useNavigation(); // Hook de navegação

  return (
    <View style={styles.container}>
      {/* Seta para Voltar */}
      <TouchableOpacity style={styles.backButton} onPress={() => navigation.goBack()}>
        <Ionicons name="arrow-back" size={30} color="#006BA6" />
      </TouchableOpacity>

      {/* Adicionando uma imagem para dar um toque visual ao app */}
      <Image 
        source={{ uri: 'https://via.placeholder.com/150' }} // Imagem do app
        style={styles.appImage}
      />

      <Text style={styles.title}>Sobre o Aplicativo</Text>

      <Text style={styles.description}>
        Este aplicativo foi desenvolvido para facilitar a reserva de salas, oferecendo uma experiência simples e intuitiva para os usuários. Com um design moderno e fácil de usar, ele visa melhorar a gestão de espaços e otimizar o tempo de todos os usuários.
      </Text>

      <View style={styles.infoContainer}>
        <Text style={styles.version}>Versão: <Text style={styles.versionText}>1.0.0</Text></Text>
        <Text style={styles.contact}>Desenvolvido por: Matheus</Text>
      </View>

      {/* Texto informativo adicional */}
      <Text style={styles.footer}>
        Para mais informações, entre em contato conosco.
      </Text>
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    padding: 20,
    backgroundColor: '#F2F2F7',
  },
  backButton: {
    position: 'absolute',
    top: 20,
    left: 20,
    padding: 10,
    backgroundColor: '#fff',
    borderRadius: 50,
    elevation: 3, // Adicionando um sombreado ao ícone
  },
  appImage: {
    width: 150,
    height: 150,
    borderRadius: 15,
    marginBottom: 20,
  },
  title: {
    fontSize: 28,
    fontWeight: 'bold',
    color: '#333',
    marginBottom: 15,
    textAlign: 'center',
  },
  description: {
    fontSize: 16,
    textAlign: 'center',
    color: '#555',
    marginBottom: 30,
    lineHeight: 22,
  },
  version: {
    fontSize: 16,
    fontWeight: '600',
    color: '#333',
    marginBottom: 5,
  },
  versionText: {
    color: '#006BA6', // Azul padrão
  },
  contact: {
    fontSize: 16,
    fontWeight: '600',
    color: '#333',
    marginBottom: 20,
  },
  footer: {
    fontSize: 14,
    color: '#888',
    marginTop: 30,
    textAlign: 'center',
  },
  infoContainer: {
    marginBottom: 30,
    alignItems: 'center',
  }
});

export default AboutAppScreen;
