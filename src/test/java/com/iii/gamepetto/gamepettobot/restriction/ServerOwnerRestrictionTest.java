package com.iii.gamepetto.gamepettobot.restriction;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.requests.restaction.MessageAction;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;

class ServerOwnerRestrictionTest {

	ServerOwnerRestriction sut = new ServerOwnerRestriction();

	@Test
	void allowCommandShouldReturnTrueWhenCalledByServerOwner() {
		//given
		Message message = mock(Message.class);
		Member member = mock(Member.class);
		given(message.getMember()).willReturn(member);
		given(member.isOwner()).willReturn(true);

		//when
		boolean result = sut.allowCommand(message);

		//then
		assertThat(result, is(true));
	}

	@Test
	void allowCommandShouldReturnFalseWithMessageWhenCallerIsNotServerOwner() {
		//given
		Message message = mock(Message.class);
		Member member = mock(Member.class);
		MessageChannel channel = mock(MessageChannel.class);
		MessageAction messageAction = mock(MessageAction.class);
		given(message.getMember()).willReturn(member);
		given(member.isOwner()).willReturn(false);
		given(message.getChannel()).willReturn(channel);
		given(channel.sendMessage(anyString())).willReturn(messageAction);

		//when
		boolean result = sut.allowCommand(message);

		//then
		assertThat(result, is(false));
		then(message.getChannel()).should(times(1)).sendMessage(anyString());
	}
}
