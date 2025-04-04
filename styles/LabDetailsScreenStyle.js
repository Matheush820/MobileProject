import { StyleSheet } from 'react-native';

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#F4F8FB',  // Fundo mais suave
    padding: 20,
  },
  detailsContainer: {
    marginTop: 30,
    paddingHorizontal: 20,
  },
  labName: {
    fontSize: 32,
    fontWeight: 'bold',
    color: '#006BA6',  // Azul mais forte para destaque
    textTransform: 'uppercase',
    marginBottom: 12,
  },
  labLocation: {
    fontSize: 18,
    color: '#666',
    fontStyle: 'italic', // Toque mais sofisticado
    marginBottom: 20,
  },
  featuresContainer: {
    flexDirection: 'row',
    flexWrap: 'wrap',
    justifyContent: 'space-between',
    marginBottom: 24,
  },
  featureItem: {
    alignItems: 'center',
    justifyContent: 'center',
    width: '30%',
    aspectRatio: 1,
    marginBottom: 15,
    backgroundColor: '#fff',
    borderRadius: 10,
    shadowColor: '#000',
    shadowOpacity: 0.1,
    shadowRadius: 5,
    elevation: 5,
    borderWidth: 1,
    borderColor: '#ddd',
  },
  featureIcon: {
    marginBottom: 5,
    fontSize: 24,
  },
  featureText: {
    fontSize: 12,
    color: '#333',
    fontWeight: '600',
    textAlign: 'center',
  },
  
  labDescriptionLabel: {
    fontSize: 20,
    fontWeight: 'bold',
    color: '#006BA6',  // Azul mais forte no título
    marginBottom: 8,
  },
  labDescription: {
    fontSize: 18,
    color: '#555',  // Texto mais suave para o corpo
    lineHeight: 26,
    marginBottom: 24,
    textAlign: 'justify',
  },
  labImage: {
    width: '100%',
    height: 240,
    borderRadius: 16,
    marginTop: 20,
    marginBottom: 20,
    borderWidth: 4,
    borderColor: '#006BA6',  // Azul claro na borda da imagem
  },
  continueButton: {
    backgroundColor: '#006BA6',  // Azul mais forte para o botão
    paddingVertical: 14,
    paddingHorizontal: 40,
    borderRadius: 12,
    alignItems: 'center',
    justifyContent: 'center',
    marginTop: 0,
    elevation: 5,
    transform: [{ scale: 1 }],
    transition: 'all 0.3s ease',
  },
  continueButtonText: {
    fontSize: 20,
    color: '#fff',
    fontWeight: 'bold',
    textTransform: 'uppercase',
  },
  continueButtonPressed: {
    transform: [{ scale: 0.98 }],
  },
});

export default styles;
