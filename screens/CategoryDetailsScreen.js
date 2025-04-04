import React, { useEffect, useState } from 'react';
import { View, Text, ScrollView, TouchableOpacity, Image } from 'react-native';
import { useRoute, useNavigation } from '@react-navigation/native';
import styles from '../styles/HomeScreenStyle';

const CategoryDetails = () => {
  const route = useRoute();
  const navigation = useNavigation();
  const { category } = route.params || {};  // Protege contra categoria indefinida
  const [labs, setLabs] = useState([]);

  useEffect(() => {
    const fetchLabsForCategory = () => {
      if (!category) {
        setLabs([]); // Se não houver categoria, não mostra nada
        return;
      }

      const labsData = {
        'Informática': [
          { id: 1, name: 'Laboratório de Informática I', location: 'Uninassau, PE', image: require('../assets/labTi.png') },
          { id: 2, name: 'Laboratório de Informática II', location: 'Uninassau, PE', image: require('../assets/labTi.png') },
        ],
        'Med Vet': [
          { id: 1, name: 'Laboratório de Med Vet I', location: 'Uninassau, PE', image: require('../assets/labMedVet.jpg') },
        ],
        'Fisioterapia': [
          { id: 1, name: 'Laboratório de Fisioterapia I', location: 'Uninassau, PE', image: require('../assets/labFisio.jpg') },
        ],
        'Engenharia': [
          { id: 1, name: 'Laboratório de Engenharia I', location: 'Uninassau, PE', image: require('../assets/labTi.png') },
        ],
      };

      setLabs(labsData[category] || []); // Carrega os laboratórios da categoria selecionada ou vazio
    };

    fetchLabsForCategory();
  }, [category]);

  if (!category) {
    return (
      <View style={styles.container}>
        <Text style={styles.title}>Categoria não encontrada!</Text>
      </View>
    );
  }

  return (
    <View style={styles.container}>
      <Text style={styles.title}>Laboratórios de {category}</Text>

      <ScrollView>
        {labs.length === 0 ? (
          <Text style={styles.emptyMessage}>Não há laboratórios disponíveis para essa categoria.</Text>
        ) : (
          labs.map(lab => (
            <TouchableOpacity key={lab.id} onPress={() => navigation.navigate('LabDetails', { lab })}>
              <View style={styles.labCard}>
                <Image source={lab.image} style={styles.labImage} />
                <View style={styles.labInfo}>
                  <Text style={styles.labName}>{lab.name}</Text>
                  <Text style={styles.labLocation}>{lab.location}</Text>
                </View>
              </View>
            </TouchableOpacity>
          ))
        )}
      </ScrollView>
    </View>
  );
};

export default CategoryDetails;
