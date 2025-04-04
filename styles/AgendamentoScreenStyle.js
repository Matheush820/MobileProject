import { StyleSheet } from 'react-native';

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#F4F8FB',  // Alterado para a cor desejada
    padding: 20,
  },
  backButton: {
    position: 'absolute',
    top: 40,
    left: 20,
    zIndex: 10,
  },
  imageContainer: {
    alignItems: 'center',
    marginBottom: 20,
  },
  labImage: {
    width: '100%',
    height: 200,
    borderRadius: 16,
    marginBottom: 20,
    borderWidth: 4,
    borderColor: '#1794B9',
  },
  labName: {
    fontSize: 24,
    fontWeight: 'bold',
    color: '#1794B9',
    marginBottom: 20,
  },
  sectionTitle: {
    fontSize: 18,
    fontWeight: 'bold',
    color: '#333',
    marginBottom: 10,
  },
  dateTimeContainer: {
    marginBottom: 20,
  },
  dateTimeBox: {
    marginBottom: 20,
  },
  boxLabel: {
    fontSize: 16,
    color: '#333',
    marginBottom: 5,
  },
  dateText: {
    fontSize: 16,
    color: '#333',
    padding: 10,
    backgroundColor: '#EAF6F9',
    borderRadius: 8,
    borderWidth: 1,
    borderColor: '#1794B9',
  },
  pickerContainer: {
    marginBottom: 20,
  },
  picker: {
    backgroundColor: '#EAF6F9',
    borderRadius: 8,
    borderWidth: 1,
    borderColor: '#1794B9',
  },
  timeSlotsContainer: {
    marginBottom: 20,
  },
  timeSlotButton: {
    padding: 10,
    marginBottom: 10,
    backgroundColor: '#EAF6F9',
    borderRadius: 8,
    borderWidth: 1,
    borderColor: '#1794B9',
    alignItems: 'center',
  },
  timeSlotText: {
    fontSize: 16,
    color: '#333',
  },
  confirmButton: {
    backgroundColor: '#1C4C77',  // Cor do botão alterada
    paddingVertical: 14,
    borderRadius: 12,
    alignItems: 'center',
    justifyContent: 'center',
    marginTop: 20,
    elevation: 5,
  },
  confirmButtonText: {
    fontSize: 18,
    color: '#fff',
    fontWeight: 'bold',
  },
  buttonDisabled: {
    backgroundColor: '#006BA6',
  },
});

export default styles;
