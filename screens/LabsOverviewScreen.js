import React, { useState } from 'react';
import { View, Text, ScrollView, TouchableOpacity, Image } from 'react-native';
import { useNavigation } from '@react-navigation/native';
import { Ionicons } from '@expo/vector-icons';
import styles from '../styles/LabsOverviewScreenStyle';

const LabsOverview = () => {
  const navigation = useNavigation();
  const [labs, setLabs] = useState([
    { id: 1, name: 'Laboratório de Informática', category: 'Informática', image: 'https://via.placeholder.com/100' },
    { id: 2, name: 'Laboratório de Med Vet', category: 'Med Vet', image: 'https://via.placeholder.com/100' },
    { id: 3, name: 'Laboratório de Fisioterapia', category: 'Fisioterapia', image: 'https://via.placeholder.com/100' },
    { id: 4, name: 'Laboratório de Engenharia', category: 'Engenharia', image: 'https://via.placeholder.com/100' },
    { id: 5, name: 'Laboratório de Química', category: 'Química', image: 'https://via.placeholder.com/100' },
    { id: 6, name: 'Laboratório de Biologia', category: 'Biologia', image: 'https://via.placeholder.com/100' },
    { id: 7, name: 'Laboratório de Física', category: 'Física', image: 'https://via.placeholder.com/100' },
    { id: 8, name: 'Laboratório de Psicologia', category: 'Psicologia', image: 'https://via.placeholder.com/100' },
  ]);

  const getColor = (category) => {
    switch (category) {
      case 'Informática':
        return '#1E90FF'; // Nova cor para Informática
      case 'Med Vet':
        return '#FF6347';
      case 'Fisioterapia':
        return '#32CD32';
      case 'Engenharia':
        return '#8A2BE2';
      case 'Química':
        return '#FF4500';
      case 'Biologia':
        return '#228B22';
      case 'Física':
        return '#FFD700';
      case 'Psicologia':
        return '#BA55D3';
      default:
        return '#808080';
    }
  };

  return (
    <View style={styles.container}>
      <Text style={styles.title}>Laboratórios Disponíveis</Text>

      {/* Scroll vertical com os laboratórios */}
      <ScrollView contentContainerStyle={styles.cardContainer}>
        <View style={styles.gridContainer}>
          {labs.map((lab) => (
            <TouchableOpacity
              key={lab.id}
              style={[styles.labCard, { backgroundColor: getColor(lab.category) }]}
              onPress={() => navigation.navigate('LabDetails', { lab })}
            >
              <Image source={{ uri: lab.image }} style={styles.labImage} />
              <View style={styles.labInfo}>
                <Text style={styles.labName}>{lab.name}</Text>
              </View>
              <Ionicons
                name={lab.category === 'Informática' ? 'laptop' : lab.category === 'Med Vet' ? 'paw' : lab.category === 'Fisioterapia' ? 'fitness' : lab.category === 'Engenharia' ? 'construct' : 'flask'}
                size={28}
                color="#fff"
                style={styles.labIcon}
              />
            </TouchableOpacity>
          ))}
        </View>
      </ScrollView>
    </View>
  );
};

export default LabsOverview;
