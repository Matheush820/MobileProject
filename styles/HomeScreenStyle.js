import { StyleSheet } from 'react-native';

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#068FB8', // Alterado para o azul do projeto
    padding: 20,
  },
  header: {
    flexDirection: 'row',
    alignItems: 'center',
    marginBottom: 20,
  },
  logo: {
    width: 40,
    height: 40,
    marginRight: 10,
  },
  title: {
    fontSize: 22,
    fontWeight: 'bold',
    color: '#FFFFFF', // Alterado para branco
  },
  searchBar: {
    flexDirection: 'row',
    backgroundColor: '#ffffff',
    borderRadius: 12,
    padding: 10,
    alignItems: 'center',
    marginBottom: 20,
    shadowColor: '#000',
    shadowOffset: { width: 0, height: 4 },
    shadowOpacity: 0.1,
    shadowRadius: 8,
  },
  searchIcon: {
    marginRight: 10,
  },
  searchInput: {
    flex: 1,
    fontSize: 16,
    color: '#333',
  },
  sectionTitle: {
    fontSize: 20,
    fontWeight: 'bold',
    color: '#FFFFFF', // Alterado para branco
    marginBottom: 10,
  },
  categoryScroll: {
    marginBottom: 20,
  },
  categoryCard: {
    padding: 15,
    borderRadius: 12,
    marginRight: 15,
    width: 120,
    height: 120,
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: '#FFFFFF', // Mantido como branco para contraste
    overflow: 'hidden',
  },
  categoryText: {
    color: '#FFFFFF', // Alterado para branco nas categorias
    fontWeight: 'bold',
    fontSize: 16,
    textAlign: 'center',
  },
  categoryIcon: {
    fontSize: 28,
    marginBottom: 5,
  },
  sectionHeader: {
    flexDirection: 'row',
    justifyContent: 'space-between',
    alignItems: 'center',
    marginBottom: 10,
  },
  viewAll: {
    marginBottom: 5,
    color: '#FFFFFF', // Alterado para branco
  },
  labCard: {
    backgroundColor: '#ffffff',
    borderRadius: 12,
    padding: 20,
    marginBottom: 15,
    flexDirection: 'row',
    alignItems: 'center',
    shadowColor: '#000',
    shadowOffset: { width: 0, height: 4 },
    shadowOpacity: 0.1,
    shadowRadius: 8,
  },
  labImage: {
    width: 70,
    height: 70,
    borderRadius: 12,
    marginRight: 15,
  },
  labInfo: {
    flex: 1,
  },
  labName: {
    fontWeight: 'bold',
    color: '#1C4C77', // Alterado para azul nos laboratórios
  },
  labLocation: {
    color: '#000000', // Para manter contraste sem ser branco puro
  },
  navBar: {
    flexDirection: 'row',
    justifyContent: 'space-around',
    paddingVertical: 15,
    backgroundColor: '#ffffff',
    borderTopLeftRadius: 15,
    borderTopRightRadius: 15,
    borderTopWidth: 1,
    borderTopColor: '#DDD',
  },
});

export default styles;
