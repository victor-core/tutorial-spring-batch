package com.ccsw.tutorialbatch.processor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import com.ccsw.tutorialbatch.model.Game;
import com.ccsw.tutorialbatch.model.GameExport;

public class GameItemProcessor implements ItemProcessor<Game, GameExport> {

    private static final Logger LOGGER = LoggerFactory.getLogger(GameItemProcessor.class);

    @Override
    public GameExport process(final Game game) {
        String availability = game.getStock() > 0 ? "Disponible" : "No disponible";
        GameExport transformedGame = new GameExport(game.getTitle(), availability);
        LOGGER.info("Converting ( {} ) into ( {} )", game, transformedGame);
        return transformedGame;
    }
}
