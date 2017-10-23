#include <iostream>

bool skdjkhflkjsdjjgsdafsfdkjhsdf (double hiiklhdksjfhkjjhjdsdf, double njkkldsfhksjdgsdjfhsdkjf){
	if(hiiklhdksjfhkjjhjdsdf == njkkldsfhksjdgsdjfhsdkjf){
		return true;
	} else {
		return false;
	}
}

bool klasddjflkjgsdbdkfjksdnksdskj (double eretasdasjklsafjaslkf, double ldkgjlknasdkfkjasldjj){
	if(eretasdasjklsafjaslkf > ldkgjlknasdkfkjasldjj){
		return true;
	} else {
		return false;
	}
}

int main(void){

	double xjsdfkjsdhfkuh24q3asg = 0, xkdskjgljasr3nassfdsgj2 = 0, sr5sgiuodsbsdliy = 0, vdfhalasdhhsdfzhd35sd = 0.7, kkjdfuebasfjkjsadhkj = 0.00001;
	std::cin >> xjsdfkjsdhfkuh24q3asg >> sr5sgiuodsbsdliy;

	xkdskjgljasr3nassfdsgj2 = xjsdfkjsdhfkuh24q3asg * xjsdfkjsdhfkuh24q3asg;
	vdfhalasdhhsdfzhd35sd += (double) 1.3 * xjsdfkjsdhfkuh24q3asg;
	vdfhalasdhhsdfzhd35sd += (double) 0.9 * xkdskjgljasr3nassfdsgj2;

	if(skdjkhflkjsdjjgsdafsfdkjhsdf(vdfhalasdhhsdfzhd35sd, sr5sgiuodsbsdliy)){
		std::cout << "yes" << std::endl;
	} else if(klasddjflkjgsdbdkfjksdnksdskj(vdfhalasdhhsdfzhd35sd, sr5sgiuodsbsdliy)){
		if((vdfhalasdhhsdfzhd35sd-sr5sgiuodsbsdliy) < kkjdfuebasfjkjsadhkj){ 
			std::cout << "yes" << std::endl;
		} else {
			std::cout << "no" << std::endl;
		}
	} else if(klasddjflkjgsdbdkfjksdnksdskj(sr5sgiuodsbsdliy, vdfhalasdhhsdfzhd35sd)){
		if((sr5sgiuodsbsdliy-vdfhalasdhhsdfzhd35sd) < kkjdfuebasfjkjsadhkj){
			std::cout << "yes" << std::endl;
		} else {
			std::cout << "no" << std::endl;
		}
	}
	return 0;
}
