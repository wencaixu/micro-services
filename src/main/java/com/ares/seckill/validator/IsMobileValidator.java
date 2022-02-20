package com.ares.seckill.validator;

import com.ares.seckill.utils.ValidatorUtil;
import org.springframework.util.ObjectUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IsMobileValidator implements ConstraintValidator<IsMobile,Long> {

    private static boolean required = false;

    @Override
    public void initialize(IsMobile constraintAnnotation) {
        required = constraintAnnotation.required();
    }

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        if(required){
            return ValidatorUtil.isMobile(String.valueOf(value));
        }else{
            if(ObjectUtils.isEmpty(value)){
                return true;
            }else{
                return ValidatorUtil.isMobile(String.valueOf(value));
            }
        }
    }
}
