import { StyleSheet } from 'react-native';

const COLORS = {
  primary: '#007AFF',
  danger: '#FF3B30',
  textPrimary: '#222',
  textSecondary: '#666',
  background: '#F2F2F7',
  white: '#FFFFFF',
  shadow: '#00000020',
};

const SPACING = {
  padding: 20,
  marginVertical: 12,
  buttonPadding: 16,
  borderRadius: 14,
};

export default StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: COLORS.background,
    padding: SPACING.padding,
  },
  profileContainer: {
    alignItems: 'center',
    marginBottom: 30,
  },
  name: {
    fontSize: 22,
    fontWeight: 'bold',
    color: COLORS.textPrimary,
    marginBottom: 4,
  },
  email: {
    fontSize: 16,
    color: COLORS.textSecondary,
  },
  button: {
    flexDirection: 'row',
    alignItems: 'center',
    justifyContent: 'center',
    width: '90%',
    padding: SPACING.buttonPadding,
    backgroundColor: COLORS.primary,
    borderRadius: SPACING.borderRadius,
    marginVertical: SPACING.marginVertical,
    elevation: 2,
    shadowColor: COLORS.shadow,
    shadowOpacity: 0.2,
    shadowOffset: { width: 0, height: 2 },
    shadowRadius: 4,
  },
  buttonText: {
    color: COLORS.white,
    fontSize: 16,
    fontWeight: 'bold',
    marginLeft: 8,
  },
  aboutButton: {
    flexDirection: 'row',
    alignItems: 'center',
    justifyContent: 'center',
    width: '90%',
    padding: SPACING.buttonPadding,
    backgroundColor: COLORS.textSecondary,
    borderRadius: SPACING.borderRadius,
    marginVertical: SPACING.marginVertical,
    elevation: 2,
    shadowColor: COLORS.shadow,
    shadowOpacity: 0.2,
    shadowOffset: { width: 0, height: 2 },
    shadowRadius: 4,
  },
  aboutText: {
    color: COLORS.white,
    fontSize: 16,
    fontWeight: 'bold',
    marginLeft: 8,
  },
  logoutContainer: {
    flex: 1,
    justifyContent: 'flex-end', // Faz o botão ir para a parte inferior
    width: '100%',
    alignItems: 'center',
    paddingBottom: 20, // Espaço na parte inferior para não encostar na borda
  },
  logoutButton: {
    flexDirection: 'row',
    alignItems: 'center',
    justifyContent: 'center',
    width: '90%', // Igual aos outros botões
    padding: SPACING.buttonPadding,
    backgroundColor: COLORS.danger,
    borderRadius: SPACING.borderRadius,
    elevation: 3,
    shadowColor: COLORS.shadow,
    shadowOpacity: 0.3,
    shadowOffset: { width: 0, height: 3 },
    shadowRadius: 5,
  },
  logoutText: {
    color: COLORS.white,
    fontSize: 16,
    fontWeight: 'bold',
    textAlign: 'center', // Centraliza corretamente o texto
  },
  
});
