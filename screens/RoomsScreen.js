import React, { useState, useEffect } from 'react';
import { View, Text, TextInput, ScrollView, TouchableOpacity, Image } from 'react-native';
import { Ionicons } from '@expo/vector-icons';
import { useNavigation } from '@react-navigation/native';
import styles from '../styles/RoomsScreenStyle'; 

const RoomsScreen = () => {
  const navigation = useNavigation();
  const [labs, setLabs] = useState([]);

  // Chamada da API (comentada por enquanto)
  useEffect(() => {
    const fetchLabs = async () => {
      try {
        // Descomente esta parte quando sua API estiver pronta
        // const response = await fetch('https://api-exemplo.com/laboratorios'); // URL fictícia
        // const dataLabs = await response.json();

        // Dados fakes para simulação
        const dataLabsMock = [
          {
            id: 1,
            name: 'Laboratório Informática I',
            location: 'Uninassau, PE',
            image: require('../assets/labTi.png'),
            description: 'Laboratório equipado com computadores para cursos de TI e desenvolvimento.',
            features: [
              { name: 'Wi-Fi', icon: 'wifi' },
              { name: 'Computadores', icon: 'desktop' },
              { name: 'Projetor', icon: 'videocam' }
            ]
          },
          {
            id: 2,
            name: 'Laboratório Informática II',
            location: 'Uninassau, PE',
            image: require('../assets/labTi.png'),
            description: 'Espaço moderno para aulas de programação e design gráfico.',
            features: [
              { name: 'Wi-Fi', icon: 'wifi' },
              { name: 'Computadores', icon: 'desktop' },
              { name: 'Projetor', icon: 'videocam' }
            ]
          },
          {
            id: 3,
            name: 'Laboratório de Medicina Veterinária',
            location: 'Uninassau, PE',
            image: require('../assets/labMedVet.jpg'),
            description: 'Equipado para práticas e estudos de anatomia e fisiologia veterinária.',
            features: [
              { name: 'Wi-Fi', icon: 'wifi' },
              { name: 'Equipamentos Veterinários', icon: 'fitness' }
            ]
          },
          {
            id: 4,
            name: 'Laboratório de Engenharia Civil',
            location: 'Uninassau, PE',
            image: require('../assets/labTi.png'),
            description: 'Laboratório com equipamentos para simulação de estruturas e cálculos de engenharia.',
            features: [
              { name: 'Wi-Fi', icon: 'wifi' },
              { name: 'Equipamentos de Medição', icon: 'bar-chart' }
            ]
          },
          {
            id: 5,
            name: 'Laboratório de Química',
            location: 'Uninassau, PE',
            image: require('../assets/labMedVet.jpg'),
            description: 'Laboratório especializado em práticas e estudos de química orgânica e inorgânica.',
            features: [
              { name: 'Wi-Fi', icon: 'wifi' },
              { name: 'Equipamentos Químicos', icon: 'flask' }
            ]
          },
          {
            id: 6,
            name: 'Laboratório de fisioterapia',
            location: 'Uninassau, PE',
            image: require('../assets/labFisio.jpg'),
            description: 'Laboratório focado em experimentos e práticas relacionadas à biologia molecular.',
            features: [
              { name: 'Wi-Fi', icon: 'wifi' },
              { name: 'Microscópios', icon: 'search' }
            ]
          },
        ];

        // Simulando a resposta da API com os dados fakes
        setLabs(dataLabsMock);

      } catch (error) {
        console.error('Erro ao buscar dados:', error);
      }
    };

    fetchLabs();
  }, []);

  return (
    <View style={styles.container}>

      <View style={styles.searchBar}>
        <TouchableOpacity onPress={() => navigation.navigate('Rooms')}>
          <Ionicons name="search" size={28} color="#333" style={styles.searchIcon} />
        </TouchableOpacity>
        <TextInput
          placeholder="Pesquise seu laboratório..."
          style={styles.searchInput}
        />
      </View>

      <Text style={styles.sectionTitle}>Laboratórios Disponíveis</Text>
      <ScrollView style={styles.labList}>
        {labs.map(lab => (
          <TouchableOpacity
            key={lab.id}
            style={styles.labCard}
            onPress={() => navigation.navigate('LabDetails', { lab })}
          >
            <Image source={lab.image} style={styles.labImage} />
            <View style={styles.labInfo}>
              <Text style={styles.labName}>{lab.name}</Text>
              <Text style={styles.labLocation}>{lab.location}</Text>
              <Text style={styles.labDescription}>{lab.description}</Text>
            </View>
          </TouchableOpacity>
        ))}
      </ScrollView>

      <View style={styles.navBar}>
        <TouchableOpacity onPress={() => navigation.navigate('Home')}>
          <Ionicons name="home" size={28} color="#1794B9" />
        </TouchableOpacity>
        <TouchableOpacity onPress={() => navigation.navigate('Rooms')}>
          <Ionicons name="search" size={28} color="#000" />
        </TouchableOpacity>
        <Ionicons name="calendar" size={28} color="#000" />
        <Ionicons name="person" size={28} color="#000" />
      </View>
    </View>
  );
};

export default RoomsScreen;
