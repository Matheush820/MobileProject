import React, { useState, useEffect } from 'react';
import { View, Text, TouchableOpacity, ScrollView, Image, Alert } from 'react-native';
import DateTimePickerModal from 'react-native-modal-datetime-picker';
import { Picker } from '@react-native-picker/picker';
import { Ionicons } from '@expo/vector-icons';
import styles from '../styles/AgendamentoScreenStyle';

const AgendamentoScreen = ({ route, navigation }) => {
  const { lab } = route.params;
  const [startDate, setStartDate] = useState(new Date());
  const [selectedCourse, setSelectedCourse] = useState('');
  const [isStartDatePickerVisible, setStartDatePickerVisible] = useState(false);
  const [selectedTimeSlot, setSelectedTimeSlot] = useState(null);

  const courses = ['Curso 1', 'Curso 2', 'Curso 3'];

  // Horários pré-definidos
  const timeSlots = [
    { label: '08:00 - 10:30', value: '08:00 - 10:30' },
    { label: '10:30 - 13:00', value: '10:30 - 13:00' },
    { label: '13:00 - 15:30', value: '13:00 - 15:30' },
    { label: '15:30 - 18:00', value: '15:30 - 18:00' },
  ];

  // Lista de horários já agendados (simulando com dados de exemplo)
  const [bookedSlots, setBookedSlots] = useState([
    { date: '2025-03-20', slot: '08:00 - 10:30' },  
    { date: '2025-03-20', slot: '10:30 - 13:00' },  
  ]);

  // Função para verificar se o horário está disponível
  const isSlotAvailable = (date, slot) => {
    return !bookedSlots.some(booking => booking.date === date && booking.slot === slot);
  };

  // Lógica para confirmar o agendamento
  const handleConfirm = async () => {
    if (!selectedCourse || !startDate || !selectedTimeSlot) {
      Alert.alert('Erro', 'Por favor, preencha todos os campos.');
      return;
    }

    // Código de consumo da API (comentado por enquanto)
    /*
    try {
      const response = await fetch('sua-api-url/agendamento', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({
          startDate,
          timeSlot: selectedTimeSlot,
          course: selectedCourse
        }),
      });

      const data = await response.json();

      if (response.ok) {
        console.log('Agendamento confirmado:', data);
        Alert.alert('Sucesso', `Agendamento confirmado para o curso ${selectedCourse}!`);
        navigation.goBack();
      } else {
        Alert.alert('Erro', data.message || 'Houve um problema ao confirmar o agendamento.');
      }
    } catch (error) {
      console.error('Erro ao confirmar o agendamento', error);
      Alert.alert('Erro', 'Houve um problema ao confirmar o agendamento. Tente novamente.');
    }
    */
  };

  // Manter a visibilidade do seletor de data/hora de início
  const handleStartDateChange = (date) => {
    setStartDate(date);
    setStartDatePickerVisible(false);
  };

  return (
    <ScrollView style={styles.container}>
      <View style={styles.imageContainer}>
        {lab.image ? (
          <Image source={lab.image} style={styles.labImage} />
        ) : (
          <Text style={styles.noImageText}>Imagem não disponível</Text>
        )}
        <TouchableOpacity style={styles.backButton} onPress={() => navigation.goBack()}>
          <Ionicons name="arrow-back" size={30} color="#fff" />
        </TouchableOpacity>
      </View>

      <Text style={styles.labName}>{lab.name}</Text>

      {/* Data de Início */}
      <Text style={styles.sectionTitle}>Data de Agendamento</Text>
      <View style={styles.dateTimeContainer}>
        <View style={styles.dateTimeBox}>
          <Text style={styles.boxLabel}>Início</Text>
          <TouchableOpacity onPress={() => setStartDatePickerVisible(true)}>
            <Text style={styles.dateText}>
              {startDate.toLocaleDateString()}
            </Text>
          </TouchableOpacity>
          <DateTimePickerModal
            isVisible={isStartDatePickerVisible}
            mode="date"
            date={startDate}
            onConfirm={handleStartDateChange}
            onCancel={() => setStartDatePickerVisible(false)}
          />
        </View>
      </View>

      {/* Seleção do Curso */}
      <Text style={styles.sectionTitle}>Selecione o Curso</Text>
      <View style={styles.pickerContainer}>
        <Picker
          selectedValue={selectedCourse}
          onValueChange={(itemValue) => setSelectedCourse(itemValue)}
          style={styles.picker}
        >
          <Picker.Item label="Selecione um Curso" value="" />
          {courses.map((course, index) => (
            <Picker.Item key={index} label={course} value={course} />
          ))}
        </Picker>
      </View>

      {/* Seleção do Horário */}
      <Text style={styles.sectionTitle}>Selecione um Horário</Text>
      <View style={styles.pickerContainer}>
        <Picker
          selectedValue={selectedTimeSlot}
          onValueChange={(itemValue) => setSelectedTimeSlot(itemValue)}
          style={styles.picker}
        >
          <Picker.Item label="Selecione um Horário" value="" />
          {timeSlots.map((slot, index) => (
            <Picker.Item
              key={index}
              label={slot.label}
              value={slot.value}
              enabled={isSlotAvailable(startDate.toLocaleDateString(), slot.value)}
            />
          ))}
        </Picker>
      </View>

      {/* Botão Confirmar */}
      <TouchableOpacity
        style={[styles.confirmButton, !selectedCourse || !startDate || !selectedTimeSlot ? styles.buttonDisabled : {}]}
        onPress={handleConfirm}
        disabled={!selectedCourse || !startDate || !selectedTimeSlot}
      >
        <Text style={styles.confirmButtonText}>Confirmar Agendamento</Text>
      </TouchableOpacity>
    </ScrollView>
  );
};

export default AgendamentoScreen;
