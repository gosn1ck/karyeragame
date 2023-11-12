package ru.karyeragame.paymentsystem.payment.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ru.karyeragame.paymentsystem.payment.dto.NewPaymentDto;
import ru.karyeragame.paymentsystem.payment.dto.PaymentDto;
import ru.karyeragame.paymentsystem.payment.model.Payment;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PaymentMapper {

    @Mappings({
            @Mapping(source = "accountIdFrom", target = "from.id"),
            @Mapping(source = "accountIdTo", target = "to.id"),
            @Mapping(source = "gameId", target = "game.id")
    })
    Payment toEntity(NewPaymentDto dto);

    @Mappings({
            @Mapping(source = "from.id", target = "accountIdFrom"),
            @Mapping(source = "to.id", target = "accountIdTo"),
            @Mapping(source = "game.id", target = "gameId")
    })
    PaymentDto toPaymentDto(Payment payment);

    List<PaymentDto> toPaymentDtoList(List<Payment> paymentList);
}
