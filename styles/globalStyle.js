import { StyleSheet } from 'react-native';

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
  },
  // Ondas modificadas para os cantos superiores e inferiores
  topWave: {
    position: 'absolute',
    top: 0,
    right: 0,
    width: 100,
    height: 100,
    backgroundColor: '#F7F1F1',
    borderBottomLeftRadius: 50,
    borderTopRightRadius: 50,
  },
  bottomWave: {
    position: 'absolute',
    bottom: 0,
    left: 0,
    width: 100,
    height: 100,
    backgroundColor: '#F7F1F1',
    borderTopRightRadius: 50,
    borderBottomLeftRadius: 50,
  },
  content: {
    justifyContent: 'center',
    alignItems: 'center',
    flex: 1,
  },
  logo: {
    width: 150,
    height: 150,
  },
  title: {
    fontSize: 40,
    color: '#fff',
    fontWeight: 'bold',
    marginTop: 20,
  },
  subtitle: {
    fontSize: 18,
    color: '#fff',
    marginTop: 10,
  },
});

export default styles;
