import { StyleSheet } from 'react-native';

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#0093B6', // Cor de fundo azul que você gosta
    padding: 20,
  },
  title: {
    fontSize: 24,
    fontWeight: 'bold',
    color: '#fff',
    marginBottom: 20,
  },
  cardContainer: {
    paddingBottom: 20,
  },
  gridContainer: {
    flexDirection: 'row',
    flexWrap: 'wrap', // Permite que os cards se movam para a próxima linha
    justifyContent: 'space-between', // Espaçamento entre as colunas
  },
  labCard: {
    backgroundColor: '#ffffff',
    borderRadius: 12,
    padding: 20,
    marginBottom: 15,
    width: '48%', // 2 cards por linha, 48% para dar espaço entre eles
    height: 180, // Tamanho fixo para manter o layout consistente
    justifyContent: 'center',
    alignItems: 'center',
    shadowColor: '#000',
    shadowOffset: { width: 0, height: 4 },
    shadowOpacity: 0.1,
    shadowRadius: 8,
    elevation: 3, // Sombras no Android
  },
  labImage: {
    width: 60,
    height: 60,
    borderRadius: 30,
    marginBottom: 10,
  },
  labInfo: {
    marginBottom: 10,
  },
  labName: {
    fontSize: 16,
    fontWeight: '600',
    color: '#333',
    textAlign: 'center',
  },
  labIcon: {
    marginTop: 10,
  },
});

export default styles;
