import React from 'react';
import { View, Text, Image, ScrollView, TouchableOpacity } from 'react-native';
import { Ionicons } from '@expo/vector-icons';
import styles from '../styles/LabDetailsScreenStyle';

const LabDetailsScreen = ({ navigation, route }) => {
  const { lab } = route.params;

  return (
    <ScrollView style={styles.container}>
      <TouchableOpacity onPress={() => navigation.goBack()} style={styles.backButton}>
        <Ionicons name="arrow-back" size={30} color="#1794B9" />
      </TouchableOpacity>

      {/* Imagem do laboratório */}
      <Image source={lab.image} style={styles.labImage} />
      
      <View style={styles.detailsContainer}>
        <Text style={styles.labName}>{lab.name}</Text>
        <Text style={styles.labLocation}>{lab.location}</Text>

        <View style={styles.featuresContainer}>
          {lab.features.map((feature, index) => (
            <View key={index} style={styles.featureItem}>
              <Ionicons name={feature.icon} size={24} color="#1794B9" /> 
              <Text style={styles.featureText}>{feature.name}</Text>
            </View>
          ))}
        </View>

        <Text style={styles.labDescriptionLabel}>Descrição</Text>
        <Text style={styles.labDescription}>{lab.description}</Text>

        {/* Botão para ir para a tela de agendamento */}
        <TouchableOpacity onPress={() => navigation.navigate('Agendamento', { lab })} style={styles.continueButton}>
          <Text style={styles.continueButtonText}>Continuar</Text>
        </TouchableOpacity>
      </View>
    </ScrollView>
  );
};

export default LabDetailsScreen;
