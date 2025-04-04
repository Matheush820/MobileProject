import { StyleSheet } from 'react-native';

export default StyleSheet.create({
  container: {
    flex: 1,
    padding: 20,
    backgroundColor: '#007BA7', // Azul padrão
  },
  topSection: {
    alignItems: 'center',
    marginBottom: 20,
    paddingTop: 50,
    backgroundColor: '#FFF',
    width: '100%',
    borderBottomLeftRadius: 50,
    borderBottomRightRadius: 50,
    paddingBottom: 20,
  },
  logo: {
    width: 100,
    height: 100,
    marginBottom: 10,
  },
  projectName: {
    fontSize: 20,
    fontWeight: 'bold',
    color: '#007BA7',
  },
  roomImage: {
    width: '100%',
    height: 200,
    borderRadius: 10,
    marginBottom: 20,
  },
  label: {
    fontSize: 16,
    fontWeight: 'bold',
    marginTop: 10,
    color: '#FFF', // Texto branco para contraste
  },
  input: {
    backgroundColor: '#FFF',
    padding: 10,
    borderRadius: 8,
    borderWidth: 1,
    borderColor: '#DDD',
    marginTop: 5,
  },
  saveButton: {
    marginTop: 20,
    backgroundColor: '#005F7F', // Azul mais escuro para destaque
    padding: 15,
    borderRadius: 10,
    alignItems: 'center',
  },
  saveButtonText: {
    color: '#FFF',
    fontSize: 16,
    fontWeight: 'bold',
  },
});
