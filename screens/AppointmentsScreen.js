import React, { useEffect, useState } from 'react';
import { View, Text, FlatList, StyleSheet, TouchableOpacity, Image } from 'react-native';
import { Ionicons } from '@expo/vector-icons';
import styles from '../styles/AppointmentsScreenStyle';

const AppointmentsScreen = ({ navigation }) => {
  const [appointmentsData, setAppointmentsData] = useState([
    {
      id: '1',
      title: 'Reunião de equipe',
      date: '2025-04-02 14:00',
      roomName: 'Sala A',
      roomImage: 'https://example.com/roomA.jpg',
      course: 'Curso de Desenvolvimento Web',
    },
    {
      id: '2',
      title: 'Consulta médica',
      date: '2025-04-05 10:30',
      roomName: 'Sala B',
      roomImage: 'https://example.com/roomB.jpg',
      course: 'Curso de Saúde e Bem-estar',
    },
    {
      id: '3',
      title: 'Aula de React Native',
      date: '2025-04-07 18:00',
      roomName: 'Sala C',
      roomImage: 'https://example.com/roomC.jpg',
      course: 'Curso de React Native',
    },
  ]);

  useEffect(() => {
    // Simulação de requisição ao backend
  }, []);

  const handleDelete = (id) => {
    setAppointmentsData(appointmentsData.filter(appointment => appointment.id !== id));
  };

  const handleEdit = (appointment) => {
    navigation.navigate('EditAppointMent', { appointment });
  };

  return (
    <View style={styles.container}>
      <Text style={styles.title}>Meus Agendamentos</Text>
      <FlatList
        data={appointmentsData}
        keyExtractor={(item) => item.id}
        renderItem={({ item }) => (
          <View style={styles.card}>
            <Image source={{ uri: item.roomImage }} style={styles.roomImage} />
            <Text style={styles.cardTitle}>{item.title}</Text>
            <Text style={styles.cardDate}>{item.date}</Text>
            <Text style={styles.roomName}>{item.roomName}</Text>
            <Text style={styles.courseName}>{item.course}</Text>

            <View style={styles.buttonContainer}>
              <TouchableOpacity onPress={() => handleEdit(item)} style={styles.button}>
                <Text style={styles.buttonText}>Editar</Text>
              </TouchableOpacity>
              <TouchableOpacity onPress={() => handleDelete(item.id)} style={styles.button}>
                <Text style={styles.buttonText}>Excluir</Text>
              </TouchableOpacity>
            </View>
          </View>
        )}
      />

      <View style={styles.navBar}>
        <TouchableOpacity onPress={() => navigation.navigate('Home')}>
          <Ionicons name="home" size={28} color="#1794B9" />
        </TouchableOpacity>

        <TouchableOpacity onPress={() => navigation.navigate('Rooms')}>
          <Ionicons name="search" size={28} color="#000" />
        </TouchableOpacity>

        <TouchableOpacity onPress={() => navigation.navigate('Appointments')}>
          <Ionicons name="calendar" size={28} color="#000" />
        </TouchableOpacity>

        <TouchableOpacity onPress={() => navigation.navigate('Profile')}>
          <Ionicons name="person" size={28} color="#000" />
        </TouchableOpacity>
      </View>
    </View>
  );
};

export default AppointmentsScreen;
