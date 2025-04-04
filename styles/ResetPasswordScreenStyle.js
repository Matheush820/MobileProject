import { StyleSheet, Dimensions } from 'react-native';

const { height, width } = Dimensions.get('window'); // Pegando altura e largura da tela

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#068FB8',
    justifyContent: 'center',
    alignItems: 'center',
  },
  topSection: {
    width: '100%', 
    height: height * 0.35, // Reduzindo a altura da parte branca
    backgroundColor: '#F7F1F1',
    borderBottomLeftRadius: 40,
    borderBottomRightRadius: 40,
    justifyContent: 'center',
    alignItems: 'center',
    position: 'absolute',
    top: 0,
    left: 0,
    right: 0,
    paddingTop: 60, // Aumentando espaço do topo
    elevation: 6,
    shadowColor: '#000',
    shadowOffset: { width: 0, height: 4 },
    shadowOpacity: 0.2,
    shadowRadius: 6,
  },
  backButton: {
    position: 'absolute',
    top: 50, // Ajuste para posicionar corretamente
    left: 20, // Mantém a seta na esquerda
    zIndex: 10,
  },
  logoContainer: {
    alignItems: 'center',
    justifyContent: 'center',
    width: width * 0.35, // Reduzindo tamanho horizontalmente
    height: width * 0.35, 
    borderRadius: width * 0.175, 
    backgroundColor: '#FFF',
    position: 'absolute',
    top: height * 0.2,
    shadowColor: '#000',
    shadowOffset: { width: 0, height: 6 },
    shadowOpacity: 0.15,
    shadowRadius: 10,
  },
  logoImage: {
    width: 120,
    height: 120,
    borderRadius: 60,
    marginBottom: 12,
    shadowColor: '#000',
    shadowOffset: { width: 0, height: 6 },
    shadowOpacity: 0.15,
    shadowRadius: 10,
    borderWidth: 3,
    borderColor: '#fff',
  },
  logoText: {
    fontSize: 36,
    fontWeight: 'bold',
    color: '#1D4C77',
    letterSpacing: 3,
    marginTop: 10,
    textTransform: 'uppercase',
  },
  logoSubText: {
    fontSize: 22,
    color: '#1D4C77',
    fontStyle: 'italic',
    transform: [{ scale: 1.05 }]
  },
  inputContainer: {
    marginTop: height * 0.2, // Movendo os inputs mais para baixo
    paddingHorizontal: 30,
    width: '100%',
    alignItems: 'center',
  },
  input: {
    width: '90%', 
    height: 50, 
    minHeight: 50, // Garante altura mínima fixa
    borderColor: '#fff',
    borderWidth: 1,
    borderRadius: 8,
    marginBottom: 15,
    paddingHorizontal: 10,
    backgroundColor: '#fff',
    fontSize: 16, 
    textAlignVertical: 'center', // Centraliza o texto no input
    includeFontPadding: false, // Remove espaços extras do texto
  },
  passwordContainer: {
    flexDirection: 'row',
    alignItems: 'center',
    backgroundColor: '#FFF',
    borderRadius: 10,
    paddingHorizontal: 15,
    height: 50, 
    marginBottom: 5,
    width: '90%',
  },
  inputPassword: {
    flex: 1,
    fontSize: 16,
    color: '#333',
    textAlignVertical: 'center', // Garante que o texto do password fique alinhado
    includeFontPadding: false, // Remove espaços extras
  },
  forgotPassword: {
    marginTop: 15,
    color: '#1C4C77',
    fontSize: 14,
    textDecorationLine: 'underline',
    textAlign: 'left',
    alignSelf: 'flex-start',
    marginLeft: 30,
  },
  buttonContainer: {
    justifyContent: 'center',
    marginTop: 30, // Movendo os botões um pouco mais para baixo
    paddingHorizontal: 20,
    width: '100%',
    alignItems: 'center',
  },
  loginButton: {
    width: '78%', 
    height: 50, 
    backgroundColor: '#1C4C77',
    justifyContent: 'center',
    alignItems: 'center',
    borderRadius: 8,
    marginBottom: 15,
  },
  loginButtonText: {
    color: '#fff',
    fontSize: 18,
    fontWeight: 'bold',
  },
  signUpLink: {
    marginTop: 23,
    color: '#FFF',
    fontSize: 14,
    textDecorationLine: 'underline',
    textAlign: 'center',
  },
  footerText: {
    marginTop: 120, // Ajustando espaçamento do rodapé
    color: '#FFF',
    fontSize: 12,
    textAlign: 'center',
  },
});

export default styles;
