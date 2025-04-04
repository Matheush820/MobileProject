import React, { useEffect, useState } from 'react';
import { View, Text, TextInput, ScrollView, TouchableOpacity, Image } from 'react-native';
import { Ionicons } from '@expo/vector-icons';
import { useNavigation } from '@react-navigation/native';
import styles from '../styles/HomeScreenStyle';

const HomeScreen = () => {
  const navigation = useNavigation();
  const [labs, setLabs] = useState([]);

  useEffect(() => {
    const fetchLabs = async () => {
      try {
        const dataLabs = [
          { id: 1, name: 'Laboratório de Informática I', location: 'Uninassau, PE', image: require('../assets/labTi.png') },
          { id: 2, name: 'Laboratório de Fisioterapia', location: 'Uninassau, PE', image: require('../assets/labFisio.jpg') },
          { id: 3, name: 'Laboratório de Med Vet', location: 'Uninassau, PE', image: require('../assets/labMedVet.jpg') },
        ];
        setLabs(dataLabs);
      } catch (error) {
        console.error('Erro ao buscar dados:', error);
      }
    };

    fetchLabs();
  }, []);

  const colors = ['#FF5733', '#33FF57', '#5733FF', '#FFC300', '#0093B6', '#AA00FF'];
  const getColor = (index) => colors[index % colors.length];

  return (
    <View style={styles.container}>
      <View style={styles.header}>
        <Image source={require('../assets/logo.png')} style={styles.logo} />
        <Text style={styles.title}>SalaFácil Space</Text>
      </View>

      <View style={styles.searchBar}>
        <TouchableOpacity onPress={() => navigation.navigate('Rooms')}>
          <Ionicons name="search" size={28} color="#000" style={styles.searchIcon} />
        </TouchableOpacity>
        <TextInput placeholder="Pesquise seu laboratório..." style={styles.searchInput} />
      </View>

      {/* Seção de Categorias */}
      <Text style={styles.sectionTitle}>Categorias</Text>
      <View style={styles.categoryHeader}>
        <ScrollView horizontal showsHorizontalScrollIndicator={false} style={styles.categoryScroll}>
          {['Informática', 'Med Vet', 'Fisioterapia', 'Engenharia'].map((category, index) => (
            <TouchableOpacity
              key={index}
              style={[styles.categoryCard, { backgroundColor: getColor(index) }]}
              onPress={() => navigation.navigate('CategoryDetails', { category })}
            >
              <Ionicons
                name={category === 'Informática' ? 'laptop' : category === 'Med Vet' ? 'paw' : category === 'Fisioterapia' ? 'fitness' : 'construct'}
                size={20}
                color="#fff"
                style={styles.categoryIcon}
              />
              <Text style={styles.categoryText}>{category}</Text>
            </TouchableOpacity>
          ))}
        </ScrollView>
        {/* Botão "Ver todas" para redirecionar para a tela com todas as categorias */}
        <TouchableOpacity
  onPress={() => {
    console.log("Explorar todos clicado");
    navigation.push('LabsOverview');  // Usando push para navegar explicitamente para a tela LabsOverview
  }}
  style={styles.viewAllButton}
>
  <Text style={styles.viewAll}>Explorar todos</Text>
</TouchableOpacity>


      </View>

      {/* Seção de Laboratórios */}
      <View style={styles.sectionHeader}>
        <Text style={styles.sectionTitle}>Laboratórios Disponíveis</Text>
        <TouchableOpacity onPress={() => navigation.navigate('Rooms')}>
          <Text style={[styles.viewAll, { textAlign: 'right' }]}>Ver todos</Text>
        </TouchableOpacity>
      </View>

      <ScrollView>
        {labs.map(lab => (
          <TouchableOpacity key={lab.id} onPress={() => navigation.navigate('LabDetails', { lab })}>
            <View style={styles.labCard}>
              <Image source={lab.image} style={styles.labImage} />
              <View style={styles.labInfo}>
                <Text style={styles.labName}>{lab.name}</Text>
                <Text style={styles.labLocation}>{lab.location}</Text>
              </View>
            </View>
          </TouchableOpacity>
        ))}
      </ScrollView>

      {/* Barra de navegação */}
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

export default HomeScreen;
